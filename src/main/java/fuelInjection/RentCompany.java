package fuelInjection;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String REPORT_FORMAT = "%s : %.0f리터".concat(NEWLINE);

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

    public List<Car> getCars() {
        return cars;
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car : cars) {
            stringBuilder.append(String.format(REPORT_FORMAT, car.getName(), car.getChargeQuantity()));
        }
        return stringBuilder.toString();
    }
}
