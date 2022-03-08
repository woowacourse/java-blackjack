package rentcompany;

public class K5 extends Car {

    public K5(int tripDistance) {
        super(13, tripDistance, "K5");
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return name;
    }
}
