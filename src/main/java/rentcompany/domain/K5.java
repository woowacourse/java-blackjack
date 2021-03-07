package rentcompany.domain;

public class K5 extends Car {
    private final String name = "K5";
    private final double tripDistance;
    private final Fuel distancePerLiter = new Fuel(13D);

    public K5(int tripDistance) {
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
