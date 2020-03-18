package RentCar.Domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String NEWLINE = System.getProperty("line.separator");

    List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder generateReport = new StringBuilder();
        for(Car car : cars) {
            generateReport.append(car.getName());
            generateReport.append(" : ");
            generateReport.append((int) car.getChargeQuantity());
            generateReport.append("리터");
            generateReport.append(NEWLINE);
        }
        return generateReport.toString();
    }
}
