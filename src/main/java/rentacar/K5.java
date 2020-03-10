package rentacar;

public class K5 extends Car {
    public K5(int distance) {
        super(distance);
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
