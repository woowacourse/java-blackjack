package rentcar;

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

    public void addCar(final Car car) {
        cars.add(car);
    }

    public String generateReport() {
        final StringBuilder report = new StringBuilder();
        for (final Car car : cars) {
            final String eachReport = String.format("%s : %d리터%s", car, (int) car.getChargeQuantity(), NEWLINE);
            report.append(eachReport);
        }
        return report.toString();
    }
}
