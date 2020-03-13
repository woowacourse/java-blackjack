package practice;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");

    List<Car> cars;

    private RentCompany() {
        this.cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public String generateReport() {
        String report = "";
        for (Car car : this.cars) {
            report = report + car.getName() + " : " + Math.round(car.getChargeQuantity())
                    + "리터" + NEWLINE;
        }
        return report;
    }
}
