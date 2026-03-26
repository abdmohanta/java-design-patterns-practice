package com.debasish.designpatterns.structural;

public class AdapterPattern {

    // Main contains the demonstration of the Singleton pattern with everything defined inside main.
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Singleton demo starting...");

        // Local singleton class defined inside main
        class Singleton {
            // private constructor prevents external instantiation
            private Singleton() {
                System.out.println("Singleton constructor invoked");
            }

            public void doSomething() {
                System.out.println("Singleton is doing something. Instance hash=" + this.hashCode());
            }
        }

        // Holder for the singleton instance and lock for lazy initialization
        final java.util.concurrent.atomic.AtomicReference<Singleton> instanceRef = new java.util.concurrent.atomic.AtomicReference<>();
        final Object lock = new Object();

        // Supplier that returns the singleton instance with double-checked locking
        java.util.function.Supplier<Singleton> getInstance = () -> {
            Singleton inst = instanceRef.get();
            if (inst == null) {
                synchronized (lock) {
                    inst = instanceRef.get();
                    if (inst == null) {
                        inst = new Singleton();
                        instanceRef.set(inst);
                    }
                }
            }
            return inst;
        };

        // Simple single-thread check
        Singleton s1 = getInstance.get();
        Singleton s2 = getInstance.get();
        System.out.println("s1 == s2: " + (s1 == s2));
        s1.doSomething();

        // Multithreaded check to demonstrate thread-safe lazy initialization
        int threads = 5;
        Thread[] threadGroup = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            threadGroup[i] = new Thread(() -> {
                Singleton inst = getInstance.get();
                System.out.println(Thread.currentThread().getName() + " -> instance: " + inst + " (hash=" + inst.hashCode() + ")");
            }, "T-" + (i + 1));
            threadGroup[i].start();
        }

        // Wait for threads to finish
        for (Thread t : threadGroup) {
            t.join();
        }

        System.out.println("Singleton demo finished.");
    }
}
