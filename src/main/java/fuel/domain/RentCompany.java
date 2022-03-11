package fuel.domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    public static final String EQUALS = " : ";
    public static final String FUEL_UNIT = "리터";

    private final List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(final Car car) {
        validateCar(car);
        cars.add(car);
    }

    private void validateCar(final Car car) {
        if (car == null) {
            throw new IllegalArgumentException("null 객체는 추가할 수 없습니다.");
        }
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();

        for (Car car : cars) {
            report.append(car.getName());
            report.append(EQUALS);
            report.append((int) car.getChargeQuantity());
            report.append(FUEL_UNIT);
            report.append("\n");
        }

        return new String(report);
    }
}
