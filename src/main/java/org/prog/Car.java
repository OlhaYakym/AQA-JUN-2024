package org.prog;

public class Car {

    public String color;
    public int milage = 0;

    // TODO: add parameter that indicates distance car travels
    public void goTo() {
        milage += 10;
        System.out.println(color + " car goes somewhere!");
        System.out.println(color + " car current milage is now " + milage);
    }
}
