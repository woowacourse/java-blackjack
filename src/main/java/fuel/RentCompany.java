package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    public static final String NEWLINE = System.getProperty("line.separator");

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
        StringBuilder carReport = new StringBuilder();
        for (Car car : cars) {
            carReport.append(car.getName())
                    .append(" : ")
                    .append((int) car.getChargeQuantity())
                    .append("리터")
                    .append(NEWLINE);
        }
        return carReport.toString();
    }
}
