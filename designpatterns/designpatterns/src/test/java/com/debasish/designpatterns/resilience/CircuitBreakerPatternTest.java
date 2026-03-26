package com.debasish.designpatterns.resilience;

import org.junit.jupiter.api.Test;

public class CircuitBreakerPatternTest {

    @Test
    void runCircuitBreakerDemo() throws InterruptedException {
        // The demo runs and prints to stdout; ensure it runs without throwing
        CircuitBreakerPattern.main(new String[0]);
    }
}

