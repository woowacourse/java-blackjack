package fuelinjection;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");

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
        for (Car car : cars) {
            String name = car.getName();
            int chargeQuantity = (int) car.getChargeQuantity();
            stringBuilder.append(String.format("%s : %d리터" + NEWLINE, name, chargeQuantity));
        }
        return stringBuilder.toString();
    }
}
