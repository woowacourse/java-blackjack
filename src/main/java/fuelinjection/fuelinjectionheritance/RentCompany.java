package fuelinjection.fuelinjectionheritance;

import java.util.ArrayList;

public class RentCompany {

    private final Cars cars;

    private RentCompany() {
        cars = new Cars(new ArrayList<>());
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        return cars.toString();
    }
}
