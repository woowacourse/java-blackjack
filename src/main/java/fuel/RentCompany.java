package fuel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {
    private static final String CAR_FUEL_REPORT_FORMAT = "%s : %d리터%n";

    private final List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public List<Car> addCar(Car car) {
        cars.add(car);
        return cars;
    }

    public String generateReport() {
        return cars.stream()
                .map(this::getCarFuelReport)
                .collect(Collectors.joining());
    }

    private String getCarFuelReport(Car car) {
        return String.format(CAR_FUEL_REPORT_FORMAT, car.getName(), car.getFuelNeeded());
    }
}

