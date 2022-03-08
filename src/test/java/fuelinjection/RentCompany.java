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
}
