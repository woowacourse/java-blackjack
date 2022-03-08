package rentcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RentCompany {

    private List<Car> cars;

    public RentCompany() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        Objects.requireNonNull(car);
        cars.add(car);
    }

    public int getCountOfCar() {
        return cars.size();
    }
}
