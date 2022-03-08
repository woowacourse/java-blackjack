package fuelinjection;

import java.util.ArrayList;
import java.util.List;

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
        final StringBuilder stringBuilder = new StringBuilder();
        for (Car car : value) {
            stringBuilder.append(car.getName())
                    .append(" : ")
                    .append((int) Math.round(car.getChargeQuantity()))
                    .append("리터\n");
        }

        return stringBuilder.toString();
    }
}
