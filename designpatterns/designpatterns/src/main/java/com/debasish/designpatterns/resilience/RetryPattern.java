package com.debasish.designpatterns.resilience;

public class RetryPattern {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Retry Pattern Demo ---");

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
                    throw new RuntimeException("Transient failure from " + name + ", remaining=" + failuresRemaining);
                }
                return "Success from " + name;
            }
        }

        class RetryPolicy {
            private final int maxAttempts;
            private final long baseDelayMillis;
            private final double multiplier;
            private final long maxDelayMillis;
            private final java.util.Random random = new java.util.Random();

            RetryPolicy(int maxAttempts, long baseDelayMillis, double multiplier, long maxDelayMillis) {
                this.maxAttempts = maxAttempts;
                this.baseDelayMillis = baseDelayMillis;
                this.multiplier = multiplier;
                this.maxDelayMillis = maxDelayMillis;
            }

            public <T> T execute(java.util.concurrent.Callable<T> action) throws Exception {
                int attempt = 0;
                while (true) {
                    attempt++;
                    try {
                        return action.call();
                    } catch (Exception ex) {
                        if (attempt >= maxAttempts) {
                            System.out.println("All attempts failed (" + attempt + "). Giving up.");
                            throw ex;
                        }

                        long delay = (long) (baseDelayMillis * Math.pow(multiplier, attempt - 1));
                        if (delay > maxDelayMillis) delay = maxDelayMillis;
                        double jitterFactor = 0.5 + random.nextDouble(); // 0.5 .. 1.5
                        long jittered = Math.max(0, (long) (delay * jitterFactor));

                        System.out.println("Attempt " + attempt + " failed: " + ex.getMessage() + ". Retrying in " + jittered + "ms");
                        try {
                            Thread.sleep(jittered);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            throw ie;
                        }
                    }
                }
            }
        }

        FlakyService svc = new FlakyService("retry-service", 2); // fails twice then succeeds
        RetryPolicy retry = new RetryPolicy(5, 200, 2.0, 2000);

        try {
            String result = retry.execute(svc);
            System.out.println("Result: " + result);
        } catch (Exception ex) {
            System.out.println("Operation failed after retries: " + ex.getMessage());
        }

        System.out.println("--- Retry Pattern Demo finished ---");
    }
}

