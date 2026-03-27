package com.debasish.designpatterns.behavioral;

public class TemplateMethodPattern {

    public static void main(String[] args) {

        // Step 1: Create abstract class (template)
        abstract class Beverage {

            // template method (final so no one can change flow)
            final void makeDrink() {

                boilWater(); // common step
                addIngredients(); // different for each drink
                serve(); // common step
            }

            void boilWater() {
                System.out.println("Boiling water"); // same for all
            }

            abstract void addIngredients(); // to be implemented

            void serve() {
                System.out.println("Serving drink"); // same for all
            }
        }

        // Step 2: Create Tea class
        class Tea extends Beverage {

            void addIngredients() {
                System.out.println("Adding Tea Leaves"); // tea specific
            }
        }

        // Step 3: Create Coffee class
        class Coffee extends Beverage {

            void addIngredients() {
                System.out.println("Adding Coffee Powder"); // coffee specific
            }
        }

        // Step 4: Use Template Method

        Beverage tea = new Tea(); // create tea object
        tea.makeDrink(); // follow template steps

        System.out.println("----");

        Beverage coffee = new Coffee(); // create coffee object
        coffee.makeDrink(); // same steps, different behavior
    }
}
