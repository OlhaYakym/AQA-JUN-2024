package org.prog;

public class Boat implements ITransport {

    @Override
    public void goTo() {
        System.out.println("Boat sails somewhere");
    }

    @Override
    public void turn(String direction) {
        System.out.println("Boat turns " + direction);
    }

    @Override
    public void stopAt(String stop) {
        System.out.println("Boat stops at " + stop);
    }

    public void setSail() {
        System.out.println("Set sail!");
    }

    @Override
    public void stopAt(String stop) {
        System.out.println("Boat stops at " + stop);
    }
}
