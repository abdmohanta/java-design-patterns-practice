package com.debasish.designpatterns.behavioral;

public class CommandPattern {
    public static void main(String[] args) {
        // Step 1: Command interface
        interface Command {
            void execute(); // method to execute command
        }

        // Step 2: Receiver class (actual work)
        class TV {
            void on() {
                System.out.println("TV is ON");
            }
            void off() {
                System.out.println("TV is OFF");
            }
        }

        // Step 3: Concrete Command (Turn ON)
        class TurnOnCommand implements Command {
            TV tv; // reference of receiver
            TurnOnCommand(TV tv) {
                this.tv = tv; // assign receiver
            }
            public void execute() {
                tv.on(); // call actual method
            }
        }

        // Step 4: Concrete Command (Turn OFF)
        class TurnOffCommand implements Command {
            TV tv;
            TurnOffCommand(TV tv) {
                this.tv = tv;
            }
            public void execute() {
                tv.off(); // call actual method
            }
        }

        // Step 5: Invoker class (Remote)
        class RemoteControl {
            Command command; // holds command
            void setCommand(Command command) {
                this.command = command; // set command
            }
            void pressButton() {
                command.execute(); // execute command
            }
        }

        // Step 6: Use Command Pattern
        TV tv = new TV(); // receiver
        Command onCommand = new TurnOnCommand(tv); // create ON command
        Command offCommand = new TurnOffCommand(tv); // create OFF command
        RemoteControl remote = new RemoteControl(); // invoker
        remote.setCommand(onCommand); // set ON command
        remote.pressButton(); // execute ON
        remote.setCommand(offCommand); // change to OFF command
        remote.pressButton(); // execute OFF
    }
}
