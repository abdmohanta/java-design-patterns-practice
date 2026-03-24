package com.debasish.designpatterns.creational;

// SingletonExample class
class SingletonExample {
    // Step 1: create a static variable of same class
    static SingletonExample instance;

    // Step 2: make constructor private so no one can create object using new
    private SingletonExample() {
        System.out.println("Constructor called");
    }

    // Step 3: provide a static method to get the singleton instance
    public static SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample(); // object created first time only
        }
        return instance;
    }
}

// Main class to demonstrate Singleton Pattern
public class SingletonPattern {

    public static void main(String[] args) {

        // Step 3: create object only if it is null (using getInstance method)
        SingletonExample obj1 = SingletonExample.getInstance(); // object created first time

        SingletonExample obj2 = SingletonExample.getInstance(); // using same object (no new creation)

        SingletonExample obj3 = SingletonExample.getInstance(); // using same object

        // check all references pointing to same object
        System.out.println("obj1 == obj2: " + (obj1 == obj2)); // true means same object
        System.out.println("obj2 == obj3: " + (obj2 == obj3)); // true means same object
        System.out.println("obj1 == obj3: " + (obj1 == obj3)); // true means same object
    }

}