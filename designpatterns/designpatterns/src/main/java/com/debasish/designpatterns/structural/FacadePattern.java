package com.debasish.designpatterns.structural;

public class FacadePattern {

    public static void main(String[] args) {

        // Step 1: Create complex subsystems

        class TV {
            void on() {
                System.out.println("TV is ON");
            }
        }

        class SoundSystem {
            void on() {
                System.out.println("Sound System is ON");
            }
        }

        class OTT {
            void playMovie() {
                System.out.println("Movie Started on OTT");
            }
        }

        // Step 2: Create Facade class (simplified interface)

        class HomeTheatreFacade {

            TV tv = new TV(); // create TV object
            SoundSystem sound = new SoundSystem(); // create sound object
            OTT ott = new OTT(); // create OTT object

            void watchMovie() {

                // user calls only this method
                tv.on(); // internally turning ON TV
                sound.on(); // turning ON sound
                ott.playMovie(); // play movie

                System.out.println("Enjoy your movie!");
            }
        }

        // Step 3: Use Facade

        HomeTheatreFacade home = new HomeTheatreFacade();

        home.watchMovie(); // single method call
    }
}