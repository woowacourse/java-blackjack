package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final List<Car> cars = new ArrayList<>();

    private RentCompany() {

    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar (Car car){
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Car car: cars) {
            stringBuilder.append(car.getName())
                    .append(" : ")
                    .append(String.format("%.0f", car.getChargeQuantity()))
                    .append("리터")
                    .append(NEWLINE);
        }
        return stringBuilder.toString();
    }
}
