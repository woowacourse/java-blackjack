package rentcar.abstractcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String REPORT_FORMAT =
        "%s : %.0f리터" + System.getProperty("line.separator");

    private final List<Car> cars;

    private RentCompany() {
        this.cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(final Car car) {
        this.cars.add(car);
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        for (Car car : this.cars) {
            sb.append(String.format(REPORT_FORMAT, car.getName(), car.getChargeQuantity()));
        }
        return sb.toString();
    }
}
