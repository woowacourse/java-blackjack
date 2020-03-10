package rentCompany.domain;

public class K5 extends Car implements CarService {
    String name;
    double distancePerLiter;
    double tripDistance;

    public K5(final double tripDistance) {
        this.name = "K5";
        this.distancePerLiter = 13;
        this.tripDistance = tripDistance;
    }

    @Override
    public double getDistancePerLiter() {
        return distancePerLiter;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getChargeQuantity() {
        return CarService.super.getChargeQuantity();
    }
}
