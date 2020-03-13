package practice;

public class K5 extends Car {
    private static final double K5_LITER = 13d;

    private String name;
    private double distance;

    public K5(double distance) {
        this.name = "practice.K5";
        this.distance = distance;
    }

    double getDistancePerLiter() {
        return K5_LITER;
    }

    double getTripDistance() {
        return this.distance;
    }

    String getName() {
        return this.name;
    }
}
