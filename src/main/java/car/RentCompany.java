package car;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private final static List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (Car car : cars) {
            report.append(String.format("%s : %d리터" + "\n", car.getName(), (int) car.getChargeQuantity()));
        }
        return report.toString();
    }

    public void addCar(final Car car) {
        cars.add(car);
    }
}
