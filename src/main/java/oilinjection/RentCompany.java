package oilinjection;

import java.util.ArrayList;
import java.util.List;
import oilinjection.car.Car;

public class RentCompany {

    private static final String REPORT_FORM = "%s : %.0f리터" + System.lineSeparator();
    private final List<Car> cars;

    private RentCompany() {
        this.cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        cars.forEach(car ->
            report.append(
                String.format(REPORT_FORM, car.getName(), car.getChargeQuantity())
            )
        );

        return report.toString();
    }
}
