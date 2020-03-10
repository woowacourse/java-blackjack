package rentacar;

public class Sonata extends Car implements Rentable {

    private static final int SONATA_DISTANCE_PER_LITER = 10;

    public Sonata(int distance) {
        super(distance);
    }

    @Override
    public double getDistancePerLiter() {
        return SONATA_DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return this.distance;
    }
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
