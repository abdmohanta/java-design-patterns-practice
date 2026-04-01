package com.debasish.designpatterns.creational;

class House {
    private String foundation;
    private String walls;
    private String roof;
    private String windows;
    private String doors;
    private String garage;
    private String garden;
    private House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.walls = builder.walls;
        this.roof = builder.roof;
        this.windows = builder.windows;
        this.doors = builder.doors;
        this.garage = builder.garage;
        this.garden = builder.garden;
    }

    @Override
    public String toString() {
        return "House{" +
                "foundation='" + foundation + '\'' +
                ", walls='" + walls + '\'' +
                ", roof='" + roof + '\'' +
                ", windows='" + windows + '\'' +
                ", doors='" + doors + '\'' +
                ", garage='" + garage + '\'' +
                ", garden='" + garden + '\'' +
                '}';
    }

    static class HouseBuilder {
        private String foundation;
        private String walls;
        private String roof;
        private String windows;
        private String doors;
        private String garage;
        private String garden;
        public HouseBuilder(String foundation, String walls, String roof) {
            this.foundation = foundation;
            this.walls = walls;
            this.roof = roof;
        }

        public HouseBuilder windows(String windows) {
            this.windows = windows;
            return this;
        }
        public HouseBuilder doors(String doors) {
            this.doors = doors;
            return this;
        }
        public HouseBuilder garage(String garage) {
            this.garage = garage;
            return this;
        }
        public HouseBuilder garden(String garden) {
            this.garden = garden;
            return this;
        }
        public House build() {
            return new House(this);
        }
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        House house1 = new House.HouseBuilder("Concrete", "Bricks", "Tiles")
                .windows("Wooden Windows")
                .doors("Steel Doors")
                .garage("2-car Garage")
                .garden("Rose Garden")
                .build();
        System.out.println("House 1:");
        System.out.println(house1);
        System.out.println("\n");
        House house2 = new House.HouseBuilder("Stone", "Stone Blocks", "Slate")
                .windows("Glass Windows")
                .doors("Wooden Doors")
                .build();
        System.out.println("House 2:");
        System.out.println(house2);
        System.out.println("\n");
        House house3 = new House.HouseBuilder("Wood", "Wood Panels", "Shingles")
                .garage("1-car Garage")
                .build();
        System.out.println("House 3:");
        System.out.println(house3);
    }
}
