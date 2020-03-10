package rentacar;

public class Sonata extends Car {

    private static final int SONATA_DISTANCE_PER_LITER = 10;

    public Sonata(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return SONATA_DISTANCE_PER_LITER;
    }

    @Override
    double getTripDistance() {
        return this.distance;
    }

    @Override
    String getName() {
        return this.getClass().getSimpleName();
    }
}
