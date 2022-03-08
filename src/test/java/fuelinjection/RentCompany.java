package fuelinjection;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private final List<Sonata> value;

    private RentCompany() {
        this.value = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public int getCarCount() {
        return value.size();
    }

    public void addCar(Sonata car) {
        value.add(car);
    }
}
