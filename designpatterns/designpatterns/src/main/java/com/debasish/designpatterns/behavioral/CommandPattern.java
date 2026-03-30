package com.debasish.designpatterns.behavioral;
public class CommandPattern {
    public static void main(String[] args) {
        interface Command {
            void execute();
        }

        class TV {
            void on() {
                System.out.println("TV is ON");
            }
            void off() {
                System.out.println("TV is OFF");
            }
        }

        class TurnOnCommand implements Command {
            TV tv;
            TurnOnCommand(TV tv) {
                this.tv = tv; // assign receiver
            }
            public void execute() {
                tv.on();
            }
        }

        class TurnOffCommand implements Command {
            TV tv;
            TurnOffCommand(TV tv) {
                this.tv = tv;
            }
            public void execute() {
                tv.off();
            }
        }

        class RemoteControl {
            Command command;
            void setCommand(Command command) {
                this.command = command;
            }
            void pressButton() {
                command.execute();
            }
        }

        TV tv = new TV();
        Command onCommand = new TurnOnCommand(tv);
        Command offCommand = new TurnOffCommand(tv);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(onCommand);
        remote.pressButton();
        remote.setCommand(offCommand);
        remote.pressButton();
    }
}
