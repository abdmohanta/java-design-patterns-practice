Resilience Patterns (package com.debasish.designpatterns.resilience)

This folder contains small, self-contained Java demos for common resilience/fault-tolerance patterns.

Files and expectations:

- CircuitBreakerPattern.java
  - Toy circuit breaker implementation with states CLOSED, OPEN, HALF_OPEN.
  - Expected output: traces showing calls, failures, and transitions (CLOSED -> OPEN -> HALF_OPEN -> CLOSED) as the simulated service fails and then recovers.

- RetryPattern.java
  - Simple retry policy: configurable max attempts, exponential backoff with jitter.
  - Expected output: attempt logs, backoff delays, and eventual success (or final failure if max attempts reached).

- TimeoutPattern.java
  - Uses an ExecutorService to run a task with a timeout; demonstrates cancelling a task that exceeds the timeout.
  - Expected output: first case completes, second case times out and is cancelled.

- Resilience4jExamples.java
  - Shows creating Resilience4j CircuitBreaker, Retry, and TimeLimiter instances if the libraries are present on the classpath. The `pom.xml` uses explicit modules (`resilience4j-core`, `resilience4j-circuitbreaker`, `resilience4j-retry`, `resilience4j-timelimiter`).
  - Note: this demo is minimal; extend it to compose decorators (TimeLimiter + Retry + CircuitBreaker) for full behavior.

How to run the demos and tests:

- Build and test (requires Maven and internet to download dependencies):
  mvn test

- Run a demo directly from your IDE by running the `main` method of any demo class.

Notes:
- The project includes JUnit tests under `src/test/java` which invoke each demo's `main` method to ensure they run without throwing exceptions.
- If Maven isn't available in your environment, you can still import the project into an IDE (IntelliJ IDEA / Eclipse) and let the IDE download dependencies and run tests.

Next steps you might want:
- Replace toy implementations with production-grade `resilience4j` usage (examples started in `Resilience4jExamples.java`).
- Add metrics and hooks to inspect circuit breaker state programmatically.
- Add configuration via application.properties or external config files.
