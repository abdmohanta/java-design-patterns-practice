package com.debasish.designpatterns.resilience;

public class Resilience4jExamples {

    public static void main(String[] args) throws Exception {
        System.out.println("--- Resilience4j Examples (runtime-check) ---");

        try {
            Class.forName("io.github.resilience4j.circuitbreaker.CircuitBreaker");
            System.out.println("resilience4j appears to be on the classpath. You can run full examples.");
            System.out.println("Example (conceptual): create CircuitBreakerConfig, RetryConfig, TimeLimiterConfig and compose decorators.");
        } catch (ClassNotFoundException e) {
            System.out.println("resilience4j classes not found on classpath.");
            System.out.println("pom.xml includes a 'resilience4j-all' dependency; run 'mvn test' or refresh project dependencies in your IDE to download it.");
        }

        System.out.println("--- Resilience4j Examples (end) ---");
    }
}
