package fuel_injection;

public class K5 extends Car {

    public K5(int distance) {
        super("K5", 13, distance);
    }

    @Override
    double getDistancePerLiter() {
        return efficiency;
    }

    @Override
    double getTripDistance() {
        return distance;
    }

    @Override
    String getName() {
        return name;
    }
}
