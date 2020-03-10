package rentacar;

public class Avante extends Car {

    private static final int AVANTE_DISTANCE_PER_LITER = 15;

    public Avante(int distance) {
        super(distance);
    }

    @Override
    double getDistancePerLiter() {
        return AVANTE_DISTANCE_PER_LITER;
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
