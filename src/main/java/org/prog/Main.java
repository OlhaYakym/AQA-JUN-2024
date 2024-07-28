package org.prog;

public class Main {

    /**
     * #############################################
     * #############################################
     * #############################################
     * ############[Car : car1 | color:Red]#########
     * #############################################
     * #############################################
     * #############################################
     * #############################################
     * #############################################
     * ###[Car : car2 | color:Blue]#################
     * ############################10###############
     *
     * @param args
     */


    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        car1.color = "Red";
        car2.color = "Yellow";

        car1.goTo();
        paintItBlack(car1);
        car1.goTo();
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
