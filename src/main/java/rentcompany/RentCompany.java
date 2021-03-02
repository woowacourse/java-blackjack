package rentcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private final List<Car> cars;


    public RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        StringBuilder builder = new StringBuilder();
        cars.forEach(car -> builder.append(car.generateReport()));
        return builder.toString();
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
