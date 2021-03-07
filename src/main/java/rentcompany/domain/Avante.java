package rentcompany.domain;

public class Avante extends Car {
    private final String name = "Avante";
    private final double tripDistance;
    private final Fuel distancePerLiter = new Fuel(15D);

    public Avante(int tripDistance) {
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
