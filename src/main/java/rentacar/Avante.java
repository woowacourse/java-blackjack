package rentacar;

public class Avante implements Rentable {

    private static final int AVANTE_DISTANCE_PER_LITER = 15;

    private int distance;

    public Avante(int distance) {
        this.distance = distance;
    }

    @Override
    public double getDistancePerLiter() {
        return AVANTE_DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return this.distance;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
