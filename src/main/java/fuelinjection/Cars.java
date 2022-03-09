package fuelinjection;

import java.util.List;

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
}
