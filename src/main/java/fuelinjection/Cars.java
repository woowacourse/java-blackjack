package fuelinjection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars {
    private final List<Car> values;

    private Cars(List<Car> values) {
        this.values = values;
    }

    public static Cars ofAllCars() {
        Car avante = new Avante();
        Car sonata = new Sonata();
        Car k5 = new K5();

        return new Cars(List.of(avante, sonata, k5));
    }

    public void injectFuel(Distance distance) {
        for (Car car : values) {
            car.injectFuel(distance);
        }
    }

    public Map<String, Integer> mapFuel() {
        Map<String, Integer> result = new HashMap<>();
        for (Car car : values) {
            result.put(car.toString(), car.getFuel());
        }
        return result;
    }
}
