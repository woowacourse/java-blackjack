package rentcompany;

public class Sonata extends Car {

    public Sonata(int tripDistance) {
        super(15, tripDistance, "Sonata");
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
