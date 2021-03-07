package rentcompany.domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final List<Car> carList = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        carList.add(car);
    }

    public int size() {
        return carList.size();
    }

    public String generateReport() {
        StringBuilder reportBuilder = new StringBuilder();

        for (Car car : carList) {
            reportBuilder.append(String.format("%s : %.0f리터" + NEWLINE, car.getName(), car.getChargeQuantity()));
        }

        return reportBuilder.toString();
    }
}
