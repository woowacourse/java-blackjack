package rentcompany;

public interface Car {
    String NEW_LINE = System.lineSeparator();

    double getDistancePerLiter();

    double getTripDistance();

    String getName();

    default double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    default String generateReport() {
        return getName() + " : " + (int)getChargeQuantity() + "리터" + NEW_LINE;
    }
}
