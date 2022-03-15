package rentcompany;

public class Sonata extends Car {

    public Sonata(double tripDistance) {
        this.distancePerLiter = 10.0;
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
        return "Sonata";
    }
}
