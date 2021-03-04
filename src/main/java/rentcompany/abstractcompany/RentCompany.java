package rentcompany.abstractcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private final List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        cars.forEach(car -> sb.append(car.getName())
            .append(" : ")
            .append((int) car.getChargeQuantity())
            .append("리터")
            .append(System.lineSeparator()));
        return sb.toString();
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
