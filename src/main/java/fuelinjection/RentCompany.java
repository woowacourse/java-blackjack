package fuelinjection;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private final List<Car> cars;

    private RentCompany() {
        this.cars = new ArrayList<Car>();
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
            report.append(car.getName());
            report.append(" : ");
            report.append((int) car.getChargeQuantity());
            report.append("리터");
            report.append(System.getProperty("line.separator"));
        }
        return report.toString();
    }
}
