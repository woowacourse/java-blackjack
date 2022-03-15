package fuel;

public class Avante extends Car {

    private static final String CAR_NAME = "Avante";
    private static final int DISTANCE_PER_LITER = 15;

    public Avante(int tripDistance) {
        super(DISTANCE_PER_LITER, tripDistance, CAR_NAME);
    }
}
