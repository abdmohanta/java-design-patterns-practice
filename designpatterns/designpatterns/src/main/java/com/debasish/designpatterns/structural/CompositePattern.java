package com.debasish.designpatterns.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {

    public static void main(String[] args) {

        // Step 1: Create a common interface using anonymous class concept
        // here we simulate Component (both file and folder will use same behavior)

        interface Component {
            void showDetails(); // common method
        }

        // Step 2: Create Leaf objects (Files)
        Component file1 = new Component() {
            public void showDetails() {
                System.out.println("File: Resume.pdf");
            }
        };

        Component file2 = new Component() {
            public void showDetails() {
                System.out.println("File: Photo.png");
            }
        };

        // Step 3: Create Composite object (Folder)
        class Folder implements Component {

            List<Component> items = new ArrayList<>(); // list to store files/folders

            public void add(Component c) {
                items.add(c); // add file or folder
            }

            public void showDetails() {

                System.out.println("Folder contains:");

                // loop through all items
                for (Component c : items) {
                    c.showDetails(); // call same method for all
                }
            }
        }

        // Step 4: Create folder and add files
        Folder folder = new Folder();
        folder.add(file1); // adding file1
        folder.add(file2); // adding file2

        // Step 5: Call method
        folder.showDetails(); // shows all items
    }
}