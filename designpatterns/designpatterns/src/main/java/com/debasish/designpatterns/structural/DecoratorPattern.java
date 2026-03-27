package com.debasish.designpatterns.structural;

public class DecoratorPattern {

    public static void main(String[] args) {

        // Step 1: Create common interface
        interface Coffee {
            int cost(); // method to get cost
        }

        // Step 2: Create basic object (Simple Coffee)
        Coffee simpleCoffee = new Coffee() {
            public int cost() {
                return 10; // base cost
            }
        };

        // Step 3: Create decorator class (Milk)
        class MilkDecorator implements Coffee {

            Coffee coffee; // reference of Coffee

            MilkDecorator(Coffee coffee) {
                this.coffee = coffee; // wrap existing object
            }

            public int cost() {
                return coffee.cost() + 5; // add milk cost
            }
        }

        // Step 4: Create decorator class (Sugar)
        class SugarDecorator implements Coffee {

            Coffee coffee; // reference

            SugarDecorator(Coffee coffee) {
                this.coffee = coffee; // wrap object
            }

            public int cost() {
                return coffee.cost() + 2; // add sugar cost
            }
        }

        // Step 5: Use decorators
        Coffee coffeeWithMilk = new MilkDecorator(simpleCoffee); // add milk
        Coffee fullCoffee = new SugarDecorator(coffeeWithMilk);  // add sugar

        // Step 6: Print final cost
        System.out.println("Final Coffee Cost: " + fullCoffee.cost()); // 17
    }
}