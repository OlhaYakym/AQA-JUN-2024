package org.prog;

public class Car {

    public String color;
    private int mileage = 10; // Initial mileage set to 10


    public void goTo(int distance) {
        mileage += distance;
        System.out.println(color + " car goes somewhere!");
        System.out.println(color + " car current mileage is now " + mileage);
    }

    public int getMileage() {
        return mileage;
    }
}
