package fuelinjection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {

    private final List<Car> value;

    private RentCompany() {
        this.value = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public int getCarCount() {
        return value.size();
    }

    public void addCar(Car car) {
        value.add(car);
    }

    public String generateReport() {
        return value.stream()
            .map(car -> car.getName() + " : " +
                (int)Math.round(car.getChargeQuantity()) + "리터" + System.lineSeparator())
            .collect(Collectors.joining());
    }
}
