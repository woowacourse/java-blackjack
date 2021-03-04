package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder builder = new StringBuilder();
        for (Car car : cars) {
            builder.append(car.getReport())
                    .append(NEWLINE);
        }
        return builder.toString();
    }
}
