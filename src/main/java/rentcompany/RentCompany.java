package rentcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {

    private List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        return cars.stream()
                .map(this::generateReportEach)
                .collect(Collectors.joining(
                        System.getProperty("line.separator"), "", System.getProperty("line.separator")));
    }

    private String generateReportEach(Car car) {
        return car.getName() + " : " + chargeQuantity(car) + "리터";
    }

    private int chargeQuantity(Car car) {
        return Double.valueOf(car.getChargeQuantity()).intValue();
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
