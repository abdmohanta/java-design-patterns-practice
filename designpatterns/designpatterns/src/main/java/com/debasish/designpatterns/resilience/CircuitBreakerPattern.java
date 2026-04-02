package com.debasish.designpatterns.resilience;

public class CircuitBreakerPattern {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Circuit Breaker Demo ---");
        class FlakyService implements java.util.concurrent.Callable<String> {
            private int failuresRemaining;
            private final String name;

            FlakyService(String name, int failures) {
                this.name = name;
                this.failuresRemaining = failures;
            }

            @Override
            public String call() throws Exception {
                if (failuresRemaining > 0) {
                    failuresRemaining--;
                    throw new RuntimeException("Service " + name + " simulated failure. Remaining: " + failuresRemaining);
                }
                return "OK from " + name;
            }
        }

        class CircuitBreaker {
            enum State { CLOSED, OPEN, HALF_OPEN }
            private State state = State.CLOSED;
            private int failureCount = 0;
            private final int failureThreshold;
            private final long openDurationMillis;
            private long openUntil = 0L;

            CircuitBreaker(int failureThreshold, long openDurationMillis) {
                this.failureThreshold = failureThreshold;
                this.openDurationMillis = openDurationMillis;
            }

            public synchronized <T> T call(java.util.concurrent.Callable<T> action) throws Exception {
                long now = System.currentTimeMillis();
                if (state == State.OPEN) {
                    if (now >= openUntil) {
                        state = State.HALF_OPEN;
                        System.out.println("Transition -> HALF_OPEN");
                    } else {
                        throw new IllegalStateException("Circuit is OPEN (calls are blocked)");
                    }
                }

                try {
                    T result = action.call();
                    failureCount = 0;
                    if (state == State.HALF_OPEN) {
                        state = State.CLOSED;
                        System.out.println("Transition -> CLOSED");
                    }
                    return result;
                } catch (Exception ex) {
                    failureCount++;
                    System.out.println("Call failed (count=" + failureCount + "): " + ex.getMessage());
                    if (failureCount >= failureThreshold) {
                        state = State.OPEN;
                        openUntil = System.currentTimeMillis() + openDurationMillis;
                        System.out.println("Transition -> OPEN for " + openDurationMillis + "ms");
                    }
                    throw ex;
                }
            }

            public synchronized State getState() {
                if (state == State.OPEN && System.currentTimeMillis() >= openUntil) {
                    state = State.HALF_OPEN;
                }
                return state;
            }
        }

        CircuitBreaker cb = new CircuitBreaker(3, 2000);
        FlakyService svc = new FlakyService("demo-service", 4);
        for (int i = 1; i <= 10; i++) {
            try {
                System.out.println("Call #" + i + " state=" + cb.getState());
                String resp = cb.call(svc);
                System.out.println("Response: " + resp);
            } catch (Exception ex) {
                System.out.println("Call #" + i + " -> exception: " + ex.getMessage());
            }
            Thread.sleep(400);
        }

        System.out.println("--- Circuit Breaker Demo finished ---");
    }
}

