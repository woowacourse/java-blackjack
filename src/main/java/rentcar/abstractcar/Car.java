package rentcar.abstractcar;

public abstract class Car {

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

}