package rentcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        cars.forEach(car -> report.append(car.getName())
                .append(" : ")
                .append(car.calculateAmountOfFuel())
                .append("리터")
                .append(NEWLINE));

        return report.toString();
    }
}
