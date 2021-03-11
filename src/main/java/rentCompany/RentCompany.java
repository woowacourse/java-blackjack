package rentCompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private final static String PRINT_FORMAT = "%s : %d리터";
    private static final String NEWLINE = System.getProperty("line.separator");
    private List<Car> cars = new ArrayList<>();

    private RentCompany() {

    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        StringBuilder builder = new StringBuilder();
        for (Car car : cars) {
            String formatedDistance = String.format(PRINT_FORMAT, car.getName(),
                (int) car.getChargeQuantity());
            builder.append(formatedDistance);
            builder.append(NEWLINE);
        }
        return builder.toString();
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }
}
