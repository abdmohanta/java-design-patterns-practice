package com.debasish.designpatterns.creational;

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        interface Button {
            void render();
        }
        class WindowsButton implements Button {
            @Override
            public void render() {
                System.out.println("Rendering Windows Button");
            }
        }
        class MacButton implements Button {
            @Override
            public void render() {
                System.out.println("Rendering Mac Button");
            }
        }
        class LinuxButton implements Button {
            @Override
            public void render() {
                System.out.println("Rendering Linux Button");
            }
        }
        interface Checkbox {
            void render();
        }
        class WindowsCheckbox implements Checkbox {
            @Override
            public void render() {
                System.out.println("Rendering Windows Checkbox");
            }
        }
        class MacCheckbox implements Checkbox {
            @Override
            public void render() {
                System.out.println("Rendering Mac Checkbox");
            }
        }
        class LinuxCheckbox implements Checkbox {
            @Override
            public void render() {
                System.out.println("Rendering Linux Checkbox");
            }
        }

        interface GUIFactory {
            Button createButton();
            Checkbox createCheckbox();
        }
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
