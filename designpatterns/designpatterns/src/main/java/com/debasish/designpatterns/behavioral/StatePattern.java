package com.debasish.designpatterns.behavioral;

public class StatePattern {
    public static void main(String[] args) {
        // Step 1: Create State interface
        interface State {
            void handle(); // behavior method
        }
        // Step 2: Create different states
        class RedState implements State {
            public void handle() {
                System.out.println("RED Light -> Stop");
            }
        }
        class GreenState implements State {
            public void handle() {
                System.out.println("GREEN Light -> Go");
            }
        }
        class YellowState implements State {
            public void handle() {
                System.out.println("YELLOW Light -> Wait");
            }
        }
        // Step 3: Context class (Traffic Signal)
        class TrafficSignal {
            State state; // current state
            void setState(State state) {
                this.state = state; // change state
            }
            void showSignal() {
                state.handle(); // call behavior based on state
            }
        }

        // Step 4: Use the State Pattern

        TrafficSignal signal = new TrafficSignal(); // create context
        signal.setState(new RedState()); // set RED
        signal.showSignal(); // Stop
        signal.setState(new GreenState()); // change to GREEN
        signal.showSignal(); // Go
        signal.setState(new YellowState()); // change to YELLOW
        signal.showSignal(); // Wait
    }
}