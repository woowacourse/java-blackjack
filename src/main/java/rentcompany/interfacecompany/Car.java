package rentcompany.interfacecompany;

public interface Car {

    double getDistancePerLiter();

    double getTripDistance();

    String getName();

    default double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }
}
