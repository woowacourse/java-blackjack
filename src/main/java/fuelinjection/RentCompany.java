package fuelinjection;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final List<Car> cars;

    private RentCompany(List<Car> cars) {
        this.cars = cars;
    }

    public static RentCompany create() {
        List<Car> cars = new ArrayList<>();
        return new RentCompany(cars);
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

    public void addCar(Car car) {
        cars.add(car);
    }
}
