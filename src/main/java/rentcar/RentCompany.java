package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        String report = "";

        for (Car car : cars) {
            report += car.getReport() + System.lineSeparator();
        }

        return report;
    }
}
