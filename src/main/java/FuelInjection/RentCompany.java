package FuelInjection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {

    public static final String UNIT = "리터";
    private static final String DELIMITER = " : ";
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
        return cars.stream()
                .map((car) -> String.format(car.getName() + DELIMITER + (int) car.getChargeQuantity() + UNIT + System.getProperty("line.separator")))
                .collect(Collectors.joining());
    }
}
