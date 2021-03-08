package rentcar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");
    private List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<Car>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        List<String> carsReports = cars.stream()
            .map(car -> generateCarReport(car))
            .collect(Collectors.toList());

        return String.join("", carsReports);
    }

    private String generateCarReport(Car car) {
        return String.format("%s : %d리터" + NEWLINE, car.getName(), (int) Math.ceil(car.getChargeQuantity()));
    }
}
