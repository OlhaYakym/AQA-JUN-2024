package org.prog;

public class Main {

    public static void main(String[] args) {

        Car redCar = new Car();
        redCar.setColor("Red");

        Car blueCar = new Car();
        blueCar.setColor("Blue");

        Car greenCar = new Car();
        greenCar.setColor("Green");

        Car yellowCar = new Car();
        yellowCar.setColor("Yellow");

        redCar.addMileage(475);
        redCar.goTo("Odessa");

        blueCar.addMileage(480);
        blueCar.goTo("Kharkiv");

        greenCar.addMileage(400);
        greenCar.goTo("Ternopil");

        yellowCar.addMileage(540);
        yellowCar.goTo("Lviv");

        System.out.println("redCar equals blueCar: " + redCar.equals(blueCar));
        System.out.println("redCar equals greenCar: " + redCar.equals(greenCar));
        System.out.println("redCar equals yellowCar: " + redCar.equals(yellowCar));
        System.out.println("blueCar equals greenCar: " + blueCar.equals(greenCar));
        System.out.println("blueCar equals yellowCar: " + blueCar.equals(yellowCar));
        System.out.println("greenCar equals yellowCar: " + greenCar.equals(yellowCar));

        System.out.println("Comparing cars by color:");
        System.out.println("redCar color equals blueCar color: " + redCar.getColor().equals(blueCar.getColor()));
        System.out.println("redCar color equals greenCar color: " + redCar.getColor().equals(greenCar.getColor()));
        System.out.println("redCar color equals yellowCar color: " + redCar.getColor().equals(yellowCar.getColor()));
        System.out.println("blueCar color equals greenCar color: " + blueCar.getColor().equals(greenCar.getColor()));
        System.out.println("blueCar color equals yellowCar color: " + blueCar.getColor().equals(yellowCar.getColor()));
        System.out.println("greenCar color equals yellowCar color: " + greenCar.getColor().equals(yellowCar.getColor()));
    }

    public static void transport(ITransport iTransport) {
        iTransport.goTo();
        iTransport.turn("left");
        iTransport.turn("right");
    }
}
