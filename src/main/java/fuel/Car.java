package fuel;

public abstract class Car {

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    abstract String getName();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    String getCarReport() {
        return String.format("%s : %d리터", getName(), (int) getChargeQuantity());
    }
}
