package fuel;

public class Sonata extends Car {

    private static final String CAR_NAME = "Sonata";
    private static final int DISTANCE_PER_LITER = 10;

    public Sonata(int tripDistance) {
        super(DISTANCE_PER_LITER, tripDistance, CAR_NAME);
    }
}
