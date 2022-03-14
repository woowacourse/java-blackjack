package rentcar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {

    private static final String REPORT_FORMAT = "%s : %.0f리터\n";

    private final List<Car> cars;

    private RentCompany(final List<Car> cars) {
        this.cars = cars;
    }

    public static RentCompany create() {
        return new RentCompany(new ArrayList<>());
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        return cars.stream()
                .map(car -> String.format(REPORT_FORMAT, car.getName(), car.getChargeQuantity()))
                .collect(Collectors.joining());
    }
}
