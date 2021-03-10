package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String COLON = " : ";
    private static final String LITER = "리터";

    private final List<Car> cars = new ArrayList<>();

    public RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();

        for (Car car : cars) {
            sb.append(car.getName()).append(COLON).append((int) car.getChargeQuantity()).append(LITER).append(NEWLINE);
        }
        return sb.toString();
    }
}
