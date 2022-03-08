package rentCompany;

import java.util.ArrayList;
import java.util.List;
import rentCompany.car.Car;

public class RentCompany {
    private final List<Car> cars;

    private RentCompany() {
        this.cars = new ArrayList<>();
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
            report.append(String.format("%s : %1.0f리터" + System.lineSeparator(), car.getName(), car.getChargeQuantity()));
        }
        return report.toString();
    }
}
