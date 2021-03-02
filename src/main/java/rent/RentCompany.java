package rent;

import rent.car.Car;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String NEWLINE = System.getProperty("line.separator");
    public static final String SEPARATOR = " : ";

    private static List<Car> cars;

    private RentCompany() {
    }

    public static RentCompany create() {
        cars = new ArrayList<>();
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for(Car car : cars) {
            report.append(String.format("%s%s%d%s%s", car.getName(), SEPARATOR, (int) car.getChargeQuantity(), "리터", NEWLINE));
        }
        return report.toString();
    }
}
