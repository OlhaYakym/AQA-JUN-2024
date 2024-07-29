package org.prog;

public class Car implements ITransport {

    private String color;
    private int mileage = 0;

    public void goTo() {
        goTo("current location", "somewhere", "nowhere");
    }

    public void goTo(String destination) {
        goTo("current location", destination, "nowhere");
    }

    public void goTo(String from, String destination) {
        goTo(from, destination, "nowhere");
    }

    public void goTo(String from, String destination, String passingThrough) {
        mileage += 10; // Increment mileage when the car goes anywhere
        System.out.println("Car is going from " + from + " to "
                + destination + " stopping at " + passingThrough);
    }

    public void turn(String direction) {
        System.out.println((color != null ? color : "A") + " car turns " + direction);
    }

    public void setColor(String newColor) {
        if (newColor != null && !newColor.trim().isEmpty()) {
            color = newColor;
        }
    }

    public String getColor() {
        return color;
    }
}
