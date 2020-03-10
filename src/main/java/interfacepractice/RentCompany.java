package interfacepractice;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    public static final String NEWLINE = System.getProperty("line.separator");

    private List<Car> cars = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car : cars) {
            stringBuilder.append(car.getName());
            stringBuilder.append(" : ");
            stringBuilder.append(car.getNeededFuel() + "리터");
            stringBuilder.append(NEWLINE);
        }
        return stringBuilder.toString();
    }
}
