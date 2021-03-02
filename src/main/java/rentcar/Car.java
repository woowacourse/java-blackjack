package rentcar;

public abstract class Car {
    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    String getReport() {
        return getName() + " : " + (int) getChargeQuantity() + "리터";
    }
}
