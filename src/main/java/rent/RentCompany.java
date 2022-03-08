package rent;

import java.util.ArrayList;
import java.util.List;
import rent.car.Car;

public class RentCompany {

    private final List<Car> carList = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        carList.add(car);
    }
}
