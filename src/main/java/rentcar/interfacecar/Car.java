package rentcar.interfacecar;

public interface Car {

    double getDistancePerLiter();

    double getTripDistance();

    default double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    default String getName() {
        return this.getClass().getSimpleName();
    }
}