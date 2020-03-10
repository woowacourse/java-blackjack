package rentCompany.domain;

public class Sonata extends Car implements CarService {
    String name;
    double distancePerLiter;
    double tripDistance;

    public Sonata(final double tripDistance) {
        this.name = "Sonata";
        this.distancePerLiter = 10;
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
