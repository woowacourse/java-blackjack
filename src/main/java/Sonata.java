public class Sonata extends Car {
    private static final double SONATA_LITER = 10d;

    private String name;
    private double distance;

    public Sonata(double distance) {
        this.name = "Sonata";
        this.distance = distance;
    }

    double getDistancePerLiter() {
        return SONATA_LITER;
    }

    double getTripDistance() {
        return this.distance;
    }

    String getName() {
        return this.name;
    }
}
