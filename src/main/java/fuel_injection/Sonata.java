package fuel_injection;

public class Sonata extends Car {

    public Sonata(int distance) {
        super("Sonata", 10, distance);
    }

    @Override
    double getDistancePerLiter() {
        return efficiency;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return name;
    }
}
