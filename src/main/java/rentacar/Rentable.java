package rentacar;

public interface Rentable {
    double getDistancePerLiter();

    double getTripDistance();

    String getName();

    default double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
