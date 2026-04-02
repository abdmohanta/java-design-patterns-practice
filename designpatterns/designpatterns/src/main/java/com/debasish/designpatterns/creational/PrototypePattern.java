package com.debasish.designpatterns.creational;

public class PrototypePattern {
    public static void main(String[] args) {
        class Student implements Cloneable {
            int id;
            String name;
            Student(int id, String name) {
                this.id = id;
                this.name = name;
            }
            protected Object clone() throws CloneNotSupportedException {
                return super.clone(); // default cloning
            }
            void show() {
                System.out.println(id + " " + name);
            }
        }
        try {
            Student s1 = new Student(1, "Debasish");
            Student s2 = (Student) s1.clone();
            s1.show();
            s2.show();
            System.out.println(s1 == s2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}