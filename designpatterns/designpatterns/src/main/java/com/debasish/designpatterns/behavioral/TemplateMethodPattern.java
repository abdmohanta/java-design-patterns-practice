package com.debasish.designpatterns.behavioral;

public class TemplateMethodPattern {
    public static void main(String[] args) {
        abstract class Beverage {
            final void makeDrink() {
                boilWater();
                addIngredients();
                serve();
            }
            void boilWater() {
                System.out.println("Boiling water");
            }
            abstract void addIngredients();
            void serve() {
                System.out.println("Serving drink");
            }
        }
        class Tea extends Beverage {
            void addIngredients() {
                System.out.println("Adding Tea Leaves");
            }
        }
        class Coffee extends Beverage {
            void addIngredients() {
                System.out.println("Adding Coffee Powder");
            }
        }
        Beverage tea = new Tea();
        tea.makeDrink();
        System.out.println("----");
        Beverage coffee = new Coffee();
        coffee.makeDrink();
    }
}
