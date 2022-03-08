package rent.car;

public class Avante extends Car {

    public Avante(int distance) {
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
