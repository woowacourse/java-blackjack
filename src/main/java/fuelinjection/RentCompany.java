package fuelinjection;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

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
        for (Car car: cars) {
            stringBuilder.append(car.getName())
                    .append(" : ")
                    .append((int) car.getChargeQuantity())
                    .append("리터")
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
