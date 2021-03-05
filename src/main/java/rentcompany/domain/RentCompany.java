package rentcompany.domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    public static final String PRINT_FORMAT = "%s : %.0f리터";
    private final List<Car> CAR_LIST = new ArrayList<>();
    private final String NEWLINE = System.getProperty("line.separator");

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        CAR_LIST.add(car);
    }

    public int size() {
        return CAR_LIST.size();
    }

    public String generateReport() {
        StringBuilder reportBuilder = new StringBuilder();

        for (Car car : CAR_LIST) {
            reportBuilder.append(String.format(PRINT_FORMAT + NEWLINE, car.getName(), car.getChargeQuantity()));
        }

        return reportBuilder.toString();
    }
}
