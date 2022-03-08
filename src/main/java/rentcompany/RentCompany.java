package rentcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {

    public static final String REPORT_FORMAT = "%s : %.0f리터%n";

    private final List<Car> cars = new ArrayList<>();

    // 인스턴스화 방지
    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
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
