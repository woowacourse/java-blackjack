package rentcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RentCompany {

    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String UNIT_OF_MEASUREMENT = "리터";
    private static final String COLON = " : ";

    private final List<Car> cars;

    public RentCompany() {
        cars = new ArrayList<>();
    }

    public void addCar(final Car car) {
        Objects.requireNonNull(car);
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        cars.forEach(car -> sb.append(car.getName())
                .append(COLON)
                .append((int) car.getChargeQuantity())
                .append(UNIT_OF_MEASUREMENT)
                .append(NEWLINE));
        return sb.toString();
    }

    public int getCountOfCar() {
        return cars.size();
    }
}
