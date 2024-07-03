package org.prog;

public class Car {

    public String color;
    public int mileage = 0;
    public int sum = 0;

    // TODO: add parameter that indicates distance car travels
    public void goTo(int distance) {
        mileage += 10;
        sum +=distance;
        System.out.println(color + " car goes somewhere!");
        System.out.println(color + " car current milage is now " + mileage);
        System.out.println(color + " car has traveled a total of miles " + sum);
    }
}
