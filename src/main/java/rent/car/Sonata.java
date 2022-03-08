package rent.car;

public class Sonata extends Car {
    public Sonata(int distance) {
        super();
    }

    @Override
    double getDistancePerLiter() {
        return 0;
    }

    @Override
    double getTripDistance() {
        return 0;
    }

    @Override
    String getName() {
        return null;
    }
}
