package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String CAR_DELIMINATOR= " : ";
    private static final String LITER = "리터";
    private static final String NEWLINE = System.lineSeparator();

    private final List<Car> cars;

    public RentCompany(List<Car> cars) {
        this.cars = cars;
    }

    public static RentCompany create() {
        return new RentCompany(new ArrayList<>());
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder chargeQuantityReport = new StringBuilder();
        for (Car car : cars) {
            chargeQuantityReport.append(car.getName())
                .append(CAR_DELIMINATOR)
                .append((int) car.getChargeQuantity())
                .append(LITER)
                .append(NEWLINE);
        }
        return chargeQuantityReport.toString();
    }
}
