package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String CAR_REPORT_FORMAT = "%s : %d리터" + System.lineSeparator();
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

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car : cars) {
            stringBuilder.append(String.format(CAR_REPORT_FORMAT, car.getName(), (int) car.getChargeQuantity()));
        }
        return stringBuilder.toString();
    }
}
