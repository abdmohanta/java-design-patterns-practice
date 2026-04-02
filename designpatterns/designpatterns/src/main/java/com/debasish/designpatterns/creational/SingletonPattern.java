package com.debasish.designpatterns.creational;

class SingletonExample {
    static SingletonExample instance;
    private SingletonExample() {
        System.out.println("Constructor called");
    }
    public static SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }
}

public class SingletonPattern {
    public static void main(String[] args) {
        SingletonExample obj1 = SingletonExample.getInstance();
        SingletonExample obj2 = SingletonExample.getInstance();
        SingletonExample obj3 = SingletonExample.getInstance();
        System.out.println("obj1 == obj2: " + (obj1 == obj2));
        System.out.println("obj2 == obj3: " + (obj2 == obj3));
        System.out.println("obj1 == obj3: " + (obj1 == obj3));
    }

}