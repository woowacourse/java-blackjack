package fuelinjection.fuelinjectionheritance;

import java.util.ArrayList;
import java.util.List;

public class Cars {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public void add(Car car) {
        cars.add(car);
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        cars.stream()
            .map(car -> car.getName() + " : " + car.getRequiredFuel() + "리터" + NEWLINE)
            .forEach(result::append);
        return result.toString();
    }
}
