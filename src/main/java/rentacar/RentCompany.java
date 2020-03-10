package rentacar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String COLON = " : ";
    private static final String FUEL_UNIT = "리터";

    private List<Car> cars;

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
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car : cars) {
            stringBuilder.append(car.getName())
                    .append(COLON)
                    .append((int) car.getChargeQuantity())
                    .append(FUEL_UNIT)
                    .append(NEWLINE);
        }
        return stringBuilder.toString();
    }
}
