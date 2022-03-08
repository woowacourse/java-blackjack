package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String COLON = " : ";
    private static final String FUEL_UNIT = "리터";

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
        StringBuilder stringBuilder = new StringBuilder();
        cars.forEach(car -> stringBuilder.append(car.getName()).append(COLON).append((int)car.getChargeQuantity())
                .append(FUEL_UNIT).append(NEWLINE));
        return stringBuilder.toString();
    }
}
