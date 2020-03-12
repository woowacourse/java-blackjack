package fuel.domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String LITER = "리터\n";
    private static final String DELIMITER = " : ";
    private List<Car> cars = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.getName());
            sb.append(DELIMITER);
            sb.append((int) car.getChargeQuantity());
            sb.append(LITER);
        }
        return sb.toString();
    }
}
