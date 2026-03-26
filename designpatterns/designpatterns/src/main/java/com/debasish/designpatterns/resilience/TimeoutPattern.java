package com.debasish.designpatterns.resilience;

public class TimeoutPattern {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Timeout Pattern Demo ---");

        // Simulated long-running task
        class LongTask implements java.util.concurrent.Callable<String> {
            private final long workMillis;

            LongTask(long workMillis) {
                this.workMillis = workMillis;
            }

            @Override
            public String call() throws Exception {
                System.out.println("Task starting, will work for " + workMillis + "ms");
                try {
                    Thread.sleep(workMillis);
                } catch (InterruptedException e) {
                    System.out.println("Task interrupted");
                    throw e;
                }
                return "Completed after " + workMillis + "ms";
            }
        }

        // Simple timeout wrapper using ExecutorService
        class TimeoutExecutor {
            private final java.util.concurrent.ExecutorService exec = java.util.concurrent.Executors.newCachedThreadPool();

            public <T> T callWithTimeout(java.util.concurrent.Callable<T> task, long timeoutMillis) throws Exception {
                java.util.concurrent.Future<T> future = exec.submit(task);
                try {
                    return future.get(timeoutMillis, java.util.concurrent.TimeUnit.MILLISECONDS);
                } catch (java.util.concurrent.TimeoutException te) {
                    future.cancel(true);
                    throw new java.util.concurrent.TimeoutException("Operation timed out after " + timeoutMillis + "ms");
                } catch (java.util.concurrent.ExecutionException ee) {
                    // unwrap
                    Throwable cause = ee.getCause();
                    if (cause instanceof Exception) throw (Exception) cause;
                    throw new RuntimeException(cause);
                }
            }

            public void shutdown() {
                exec.shutdownNow();
            }
        }

        TimeoutExecutor tex = new TimeoutExecutor();

        // Case 1: task completes within timeout
        try {
            String r1 = tex.callWithTimeout(new LongTask(300), 1000);
            System.out.println("Task finished: " + r1);
        } catch (Exception ex) {
            System.out.println("Case 1 exception: " + ex.getMessage());
        }

        // Case 2: task exceeds timeout and is cancelled
        try {
            String r2 = tex.callWithTimeout(new LongTask(1500), 800);
            System.out.println("Task finished: " + r2);
        } catch (Exception ex) {
            System.out.println("Case 2 exception: " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }

        tex.shutdown();

        System.out.println("--- Timeout Pattern Demo finished ---");
    }
}

