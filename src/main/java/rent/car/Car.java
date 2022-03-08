package rent.car;

public abstract class Car {

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

}
