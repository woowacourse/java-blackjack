package fuelinjection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class RentCompany {

    private final List<Car> value;

    private RentCompany() {
        this.value = new ArrayList<>();
    }

    static RentCompany create() {
        return new RentCompany();
    }

    int getCarCount() {
        return value.size();
    }

    void addCar(Car car) {
        value.add(car);
    }

    String generateReport() {
        return value.stream()
            .map(car -> car.getName() + " : " +
                (int)Math.round(car.getChargeQuantity()) + "리터" + System.lineSeparator())
            .collect(Collectors.joining());
    }
}
