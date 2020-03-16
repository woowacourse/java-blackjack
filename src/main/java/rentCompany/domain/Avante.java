package rentCompany.domain;

public class Avante extends Car implements CarService {
    String name;
    double distancePerLiter;
    double tripDistance;

    public Avante(final double tripDistance) {
        this.name = "Avante";
        this.distancePerLiter = 15;
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
