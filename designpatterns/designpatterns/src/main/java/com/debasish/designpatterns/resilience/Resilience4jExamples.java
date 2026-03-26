package com.debasish.designpatterns.resilience;

public class Resilience4jExamples {

    public static void main(String[] args) throws Exception {
        System.out.println("--- Resilience4j Examples (runtime-check) ---");

        // We avoid compile-time dependency on resilience4j by checking its presence via reflection.
        try {
            Class.forName("io.github.resilience4j.circuitbreaker.CircuitBreaker");
            System.out.println("resilience4j appears to be on the classpath. You can run full examples.");
            System.out.println("Example (conceptual): create CircuitBreakerConfig, RetryConfig, TimeLimiterConfig and compose decorators.");
            // Note: full examples require the dependency and are best executed from an IDE or after running `mvn test` to download dependencies.
        } catch (ClassNotFoundException e) {
            System.out.println("resilience4j classes not found on classpath.");
            System.out.println("pom.xml includes a 'resilience4j-all' dependency; run 'mvn test' or refresh project dependencies in your IDE to download it.");
        }

        System.out.println("--- Resilience4j Examples (end) ---");
    }
}
