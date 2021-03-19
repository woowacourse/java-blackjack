package rentcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String STRING_FORMAT = "%s : %.0f리터" + System.getProperty("line.separator");

    private final List<Car> cars = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        cars.stream()
                .forEach(car -> {
                    sb.append(String.format(STRING_FORMAT, car.getName(), car.getChargeQuantity()));
                });
        return sb.toString();
    }
}
