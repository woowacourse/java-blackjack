package fuelinjection;

import fuelinjection.car.Car;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private final List<Car> garage = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        garage.add(car);
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car : garage) {
            String carReport = car.getName() + " : " + ((int) car.getChargeQuantity()) + "리터";
            stringBuilder.append(carReport).append("\n");
        }
        return stringBuilder.toString();
    }
}
