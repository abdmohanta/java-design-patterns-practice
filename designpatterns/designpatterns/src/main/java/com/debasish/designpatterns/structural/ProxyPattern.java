package com.debasish.designpatterns.structural;

public class ProxyPattern {

    public static void main(String[] args) {

        // Step 1: Create common interface
        interface Internet {
            void connect(String site); // method to access website
        }

        // Step 2: Real object (actual implementation)
        class RealInternet implements Internet {
            public void connect(String site) {
                System.out.println("Connecting to " + site); // actual connection
            }
        }

        // Step 3: Proxy class (controls access)
        class ProxyInternet implements Internet {

            Internet realInternet = new RealInternet(); // real object

            public void connect(String site) {

                // block some sites
                if (site.equals("blocked.com")) {
                    System.out.println("Access Denied to " + site); // restriction
                } else {
                    realInternet.connect(site); // allow access
                }
            }
        }

        // Step 4: Use proxy instead of real object
        Internet internet = new ProxyInternet();

        internet.connect("google.com");   // allowed
        internet.connect("blocked.com");  // blocked
    }
}