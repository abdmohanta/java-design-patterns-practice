Resilience Patterns

This directory contains demo implementations for common resilience/fault-tolerance patterns.

Files:
- `CircuitBreakerPattern.java` - toy circuit breaker local implementation. Expected output: shows state transitions from CLOSED -> OPEN -> HALF_OPEN -> CLOSED depending on failures and open duration. The demo prints calls and failure counts.
- `RetryPattern.java` - simple retry policy with exponential backoff and jitter. Expected output: attempts with retry delays and eventual success (or final failure after max attempts).
- `TimeoutPattern.java` - uses an ExecutorService to run tasks with a timeout. Expected output: first case completes within timeout; second case times out and is cancelled.
- `Resilience4jExamples.java` - a short demo that demonstrates presence of Resilience4j on the classpath. For production examples, the pom includes explicit Resilience4j modules (`resilience4j-core`, `resilience4j-circuitbreaker`, `resilience4j-retry`, `resilience4j-timelimiter`).

Notes:
- These examples are intentionally minimal and self-contained for learning. They are safe to run locally and print traces to stdout.
- For production use, prefer using a mature library like `resilience4j` and integrate it with metrics collection and configuration.

Running tests:
- The repository includes basic JUnit tests that run each demo's `main` method. Use `mvn test` to run them (requires internet to download test dependencies).
