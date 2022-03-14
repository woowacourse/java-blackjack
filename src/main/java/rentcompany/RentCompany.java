package rentcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {

    private final List<Car> cars;

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
        return cars.stream()
                .map(this::generateCarReport)
                .collect(Collectors.joining());
    }

    private String generateCarReport(Car car) {
        return String.format("%s : %d리터%s", car.getName(), (int) car.getChargeQuantity(), System.lineSeparator());
    }
}