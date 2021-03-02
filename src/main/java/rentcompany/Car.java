package rentcompany;

public interface Car {
    String NEW_LINE = System.lineSeparator();

    String getName();

    double getDistancePerLiter();

    double getTripDistance();

    double getChargeQuantity();

    String generateReport();
}
