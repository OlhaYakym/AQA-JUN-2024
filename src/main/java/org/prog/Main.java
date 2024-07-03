package org.prog;

public class Main {

    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        car1.color = "Green";
        car2.color = "Yellow";
        car3.color = "White";

        car1.goTo(15);
        paintItBlack(car1);
        car1.goTo(125);

        car2.goTo(10);
        paintItRed(car2);
        car2.goTo(118);

    }

       public static void paintItRed(Car carToPaint) {
        carToPaint.color = "Red";
    }

    public static void paintItBlack(Car carToPaint) {
        carToPaint.color = "Black";
    }

    public static void increment(int i) {
        System.out.println(">>" + i);
        i = i + 100;
        System.out.println(">>" + i);
    }
}
