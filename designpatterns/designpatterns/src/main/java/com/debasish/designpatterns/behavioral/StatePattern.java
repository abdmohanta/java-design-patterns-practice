package com.debasish.designpatterns.behavioral;

public class StatePattern {
    public static void main(String[] args) {
        interface State {
            void handle();
        }
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
        class TrafficSignal {
            State state;
            void setState(State state) {
                this.state = state;
            }
            void showSignal() {
                state.handle();
            }
        }

        TrafficSignal signal = new TrafficSignal();
        signal.setState(new RedState());
        signal.showSignal();
        signal.setState(new GreenState());
        signal.showSignal();
        signal.setState(new YellowState());
        signal.showSignal();
    }
}