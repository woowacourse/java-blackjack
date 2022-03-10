package rentcompany;

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

    public String generateReport() {
        return null;
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
