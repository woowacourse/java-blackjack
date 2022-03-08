package RentCar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEW_LINE = "\n";

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

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();

    /*    cars.stream()
                .map(car -> car.getData())
                .map(stringBuilder::append);
        */
        cars.forEach(car -> stringBuilder.append(car.getData() + NEW_LINE));

        return stringBuilder.toString();
    }
}
