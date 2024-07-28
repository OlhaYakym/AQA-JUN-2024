package org.prog;

public class Car implements ITransport {

    private String color;
    private int mileage = 0;

    public void goTo() {
        mileage += 10;
        System.out.println("Car goes somewhere");
    }

    public void turn(String direction) {
        System.out.println(color + " car turns " + direction);
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

    public void addMileage(int additionalMileage) {
        mileage += additionalMileage;
    }

    @Override
    public void stopAt(String stop) {
        System.out.println("Car stops at " + stop);
    }
}
