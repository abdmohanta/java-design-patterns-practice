package com.debasish.designpatterns.creational;

public class PrototypePattern {

    public static void main(String[] args) {

        // Step 1: Create a class which supports cloning
        class Student implements Cloneable {

            int id;
            String name;

            // constructor
            Student(int id, String name) {
                this.id = id;
                this.name = name;
            }

            // override clone method
            protected Object clone() throws CloneNotSupportedException {
                return super.clone(); // default cloning
            }

            void show() {
                System.out.println(id + " " + name);
            }
        }

        try {

            // Step 2: Create original object
            Student s1 = new Student(1, "Debasish");

            // Step 3: Clone the object
            Student s2 = (Student) s1.clone(); // copying object

            // Step 4: Print both objects
            s1.show(); // original
            s2.show(); // cloned

            // Step 5: Check different objects
            System.out.println(s1 == s2); // false (different objects)

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}