package rentCompany;

import java.util.ArrayList;
import java.util.List;
import rentCompany.car.Car;

public class RentCompany {
    private final List<Car> cars;

    private RentCompany() {
        this.cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
