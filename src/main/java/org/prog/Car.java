package org.prog;

import java.util.Objects;

public class Car implements ITransport {

    private String color;
    private int mileage = 0;

    @Override
    public void goTo() {
        mileage += 10;
        goTo("somewhere");
    }

    public void goTo(String destination) {
        goTo("Kyiv", destination);
    }

    public void goTo(String from, String destination) {
        goTo(from, destination, "the destination");
    }

    public void goTo(String from, String destination, String passingThrough) {
        System.out.println("Car is going from " + from + " to "
                + destination + " stopping at " + passingThrough + " with a distance traveled of " + mileage + " km.");
    }

    @Override
    public void turn(String direction) {
        System.out.println(color + " car turns " + direction);
    }

    @Override
    public void stopAt(String stop) {
        System.out.println("Car stops at " + stop);
    }

    public void setColor(String newColor) {
        if (newColor != null) {
            color = newColor;
        }
    }

    public String getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public void addMileage(int distance) {
        mileage += distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Car)) return false;
        Car otherCar = (Car) obj;
        return mileage == otherCar.mileage && Objects.equals(color, otherCar.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, mileage);
    }
}
