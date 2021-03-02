package rentcompany.domain;

public class Sonata extends Car {
    private final String name = "Sonata";
    private final double tripDistance;
    private final Fuel distancePerLiter = new Fuel(10D);

    public Sonata(int tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    double getDistancePerLiter() {
        return distancePerLiter.getQuantity();
    }

    @Override
    double getTripDistance() {
        return tripDistance;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    double getChargeQuantity() {
        return super.getChargeQuantity();
    }
}
