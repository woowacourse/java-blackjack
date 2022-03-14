package rentcompany;

public class K5 extends Car {

    public K5(double tripDistance) {
        this.distancePerLiter = 13.0;
        this.tripDistance = tripDistance;
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
        return "K5";
    }
}
