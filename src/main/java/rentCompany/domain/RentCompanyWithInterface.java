package rentCompany.domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompanyWithInterface {
    public static final String NEWLINE = System.lineSeparator();
    private List<CarService> cars;

    private RentCompanyWithInterface() {
        cars = new ArrayList<>();
    }

    public static RentCompanyWithInterface create() {
        return new RentCompanyWithInterface();
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();

        for (final CarService car : cars) {
            stringBuilder.append(car.getName()
                + " : "
                + (int)car.getChargeQuantity()
                + "리터"
                + NEWLINE
            );
        }

        return stringBuilder.toString();
    }

    public void addCar(final CarService car) {
        cars.add(car);
    }
}
