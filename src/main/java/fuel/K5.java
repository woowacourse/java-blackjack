package fuel;

public class K5 extends Car {

    private static final String CAR_NAME = "K5";
    private static final int DISTANCE_PER_LITER = 13;

    public K5(int tripDistance) {
        super(DISTANCE_PER_LITER, tripDistance, CAR_NAME);
    }
}
