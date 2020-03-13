package practice;

public class Avante extends Car {
    private static final double AVANTE_LITER = 15d;

    private String name;
    private double distance;

    public Avante(double distance) {
        this.name = "practice.Avante";
        this.distance = distance;
    }

    double getDistancePerLiter() {
        return AVANTE_LITER;
    }

    double getTripDistance() {
        return this.distance;
    }

    String getName() {
        return this.name;
    }
}
