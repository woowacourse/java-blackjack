package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public int size() {
        return cars.size();
    }
}
