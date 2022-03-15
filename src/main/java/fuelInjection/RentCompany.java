package fuelInjection;

import fuelInjection.car.Car;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final RentCompany rentInstance = new RentCompany();
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String GENERATE_REPORT = "%s : %.0f리터" + NEWLINE;

    private final List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return rentInstance;
    }

    public void addCar(final Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder result = new StringBuilder();
        for (Car car : cars) {
            result.append(String.format(GENERATE_REPORT, car.getName(), car.getChargeQuantity()));
        }
        return result.toString();
    }
}
