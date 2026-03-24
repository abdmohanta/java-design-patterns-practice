package com.debasish.designpatterns.creational;

public class AbstractFactoryPattern {

    public static void main(String[] args) {

        // Abstract Product 1 - Button interface
        interface Button {
            void render();
        }

        // Concrete Product 1a - Windows Button
        class WindowsButton implements Button {
            @Override
            public void render() {
                System.out.println("Rendering Windows Button");
            }
        }

        // Concrete Product 1b - Mac Button
        class MacButton implements Button {
            @Override
            public void render() {
                System.out.println("Rendering Mac Button");
            }
        }

        // Concrete Product 1c - Linux Button
        class LinuxButton implements Button {
            @Override
            public void render() {
                System.out.println("Rendering Linux Button");
            }
        }

        // Abstract Product 2 - Checkbox interface
        interface Checkbox {
            void render();
        }

        // Concrete Product 2a - Windows Checkbox
        class WindowsCheckbox implements Checkbox {
            @Override
            public void render() {
                System.out.println("Rendering Windows Checkbox");
            }
        }

        // Concrete Product 2b - Mac Checkbox
        class MacCheckbox implements Checkbox {
            @Override
            public void render() {
                System.out.println("Rendering Mac Checkbox");
            }
        }

        // Concrete Product 2c - Linux Checkbox
        class LinuxCheckbox implements Checkbox {
            @Override
            public void render() {
                System.out.println("Rendering Linux Checkbox");
            }
        }

        // Abstract Factory interface
        interface GUIFactory {
            Button createButton();
            Checkbox createCheckbox();
        }

        // Concrete Factory 1 - Windows Factory
        class WindowsFactory implements GUIFactory {
            @Override
            public Button createButton() {
                return new WindowsButton();
            }

            @Override
            public Checkbox createCheckbox() {
                return new WindowsCheckbox();
            }
        }

        // Concrete Factory 2 - Mac Factory
        class MacFactory implements GUIFactory {
            @Override
            public Button createButton() {
                return new MacButton();
            }

            @Override
            public Checkbox createCheckbox() {
                return new MacCheckbox();
            }
        }

        // Concrete Factory 3 - Linux Factory
        class LinuxFactory implements GUIFactory {
            @Override
            public Button createButton() {
                return new LinuxButton();
            }

            @Override
            public Checkbox createCheckbox() {
                return new LinuxCheckbox();
            }
        }

        // Application class that uses the factory
        class Application {
            private Button button;
            private Checkbox checkbox;

            public Application(GUIFactory factory) {
                button = factory.createButton();
                checkbox = factory.createCheckbox();
            }

            public void render() {
                button.render();
                checkbox.render();
            }
        }

        // Demonstrate Abstract Factory Pattern
        System.out.println("=== Creating Windows GUI ===");
        GUIFactory windowsFactory = new WindowsFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.render();

        System.out.println("\n=== Creating Mac GUI ===");
        GUIFactory macFactory = new MacFactory();
        Application macApp = new Application(macFactory);
        macApp.render();

        System.out.println("\n=== Creating Linux GUI ===");
        GUIFactory linuxFactory = new LinuxFactory();
        Application linuxApp = new Application(linuxFactory);
        linuxApp.render();

        System.out.println("\n=== Creating multiple families ===");
        GUIFactory[] factories = {
            new WindowsFactory(),
            new MacFactory(),
            new LinuxFactory()
        };

        String[] osNames = {"Windows", "Mac", "Linux"};

        for (int i = 0; i < factories.length; i++) {
            System.out.println("\n--- " + osNames[i] + " Components ---");
            Button btn = factories[i].createButton();
            Checkbox chk = factories[i].createCheckbox();
            btn.render();
            chk.render();
        }
    }

}
