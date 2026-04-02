package com.debasish.designpatterns.creational;

interface Shape {
    void draw();
    void calculateArea();
}
class Circle implements Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }

    @Override
    public void calculateArea() {
        double area = Math.PI * radius * radius;
        System.out.println("Area of Circle: " + area);
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }

    @Override
    public void calculateArea() {
        double area = width * height;
        System.out.println("Area of Rectangle: " + area);
    }
}

class Triangle implements Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Triangle");
    }

    @Override
    public void calculateArea() {
        double area = (base * height) / 2;
        System.out.println("Area of Triangle: " + area);
    }
}

class ShapeFactory {
    public static Shape createShape(String shapeType, double... dimensions) {
        if (shapeType == null || shapeType.isEmpty()) {
            return null;
        }

        switch (shapeType.toLowerCase()) {
            case "circle":
                if (dimensions.length > 0) {
                    return new Circle(dimensions[0]);
                }
                break;

            case "rectangle":
                if (dimensions.length >= 2) {
                    return new Rectangle(dimensions[0], dimensions[1]);
                }
                break;

            case "triangle":
                if (dimensions.length >= 2) {
                    return new Triangle(dimensions[0], dimensions[1]);
                }
                break;

            default:
                System.out.println("Unknown shape type: " + shapeType);
        }
        return null;
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        Shape shape1 = ShapeFactory.createShape("circle", 5.0);
        shape1.draw();
        shape1.calculateArea();
        System.out.println("\n");
        Shape shape2 = ShapeFactory.createShape("rectangle", 10.0, 20.0);
        shape2.draw();
        shape2.calculateArea();
        System.out.println("\n");

        Shape shape3 = ShapeFactory.createShape("triangle", 15.0, 8.0);
        shape3.draw();
        shape3.calculateArea();
        System.out.println("\n");

        String[] shapes = {"circle", "rectangle", "triangle"};
        double[][] dimensions = {{7.0}, {12.0, 18.0}, {10.0, 6.0}};

        for (int i = 0; i < shapes.length; i++) {
            Shape shape = ShapeFactory.createShape(shapes[i], dimensions[i]);
            if (shape != null) {
                shape.draw();
                shape.calculateArea();
                System.out.println();
            }
        }
    }

}
