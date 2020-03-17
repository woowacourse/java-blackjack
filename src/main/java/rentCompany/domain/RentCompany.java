package rentCompany.domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    public static final String NEWLINE = System.lineSeparator();
    private List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();

        for (final Car car : cars) {
            stringBuilder.append(car.getName()
                + " : "
                + (int)car.getChargeQuantity()
                + "리터"
                + NEWLINE
            );
        }

        return stringBuilder.toString();
    }

    public void addCar(final Car car) {
        cars.add(car);
    }
}
