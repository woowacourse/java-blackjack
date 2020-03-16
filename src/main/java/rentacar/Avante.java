package rentacar;

public class Avante extends Car implements Rentable {

    private static final int AVANTE_DISTANCE_PER_LITER = 15;

    public Avante(int distance) {
        super(distance);
    }

    @Override
    public double getDistancePerLiter() {
        return AVANTE_DISTANCE_PER_LITER;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double getTripDistance() {
        return this.distance;
    }

    @Override
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
