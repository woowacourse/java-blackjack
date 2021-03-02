package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String REPORT_FORMAT = "%s : %d리터" + NEWLINE;

    private final List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        cars.forEach(car -> {
            stringBuilder.append(String.format(REPORT_FORMAT, car.getName(), (int) car.getChargeQuantity()));
        });
        return stringBuilder.toString();
    }
}
