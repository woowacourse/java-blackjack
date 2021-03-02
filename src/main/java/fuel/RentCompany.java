package fuel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {
    private final List<Car> cars = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        return cars.stream()
                .map(car -> String.format("%s : %d리터%s", car.getName(), (int) car.getChargeQuantity(), System.getProperty("line.separator")))
                .collect(Collectors.joining());
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
