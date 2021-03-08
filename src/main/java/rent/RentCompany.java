package rent;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (Car car : cars) {
            report.append(car.getName())
                    .append(" : ")
                    .append((int) car.getChargeQuantity())
                    .append("리터")
                    .append(NEWLINE);
        }
        return report.toString();
    }
}
