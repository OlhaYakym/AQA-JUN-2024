package org.prog;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Car> carList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            if (i % 2 == 0) {
                car.setColor("White");
            } else {
                car.setColor("Black");
            }
            car.addMileage(i * 10 + 10);
            carList.add(car);
        }

        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println("Car " + i + ": Color = " + car.getColor() + ", Mileage = " + car.getMileage() + " km");
        }
    }

    public static void transport(ITransport iTransport) {
        iTransport.goTo();
        iTransport.turn("left");
        iTransport.turn("right");
    }
}
