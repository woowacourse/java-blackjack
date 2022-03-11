package fuelinjection;

import java.util.ArrayList;
import java.util.List;

import fuelinjection.car.Car;

public class RentCompany {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String MESSAGE_FORMAT_OF_CAR_OF_CHARGE_QUANTITY = "%s : %d리터";

    private final List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(final Car car) {
        cars.add(car);
    }

    public String generateReport() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Car car : cars) {
            final String name = car.getName();
            final int chargeQuantity = (int) car.getChargeQuantity();
            stringBuilder.append(String.format(MESSAGE_FORMAT_OF_CAR_OF_CHARGE_QUANTITY, name, chargeQuantity));
            stringBuilder.append(LINE_SEPARATOR);
        }
        return stringBuilder.toString();
    }
}
