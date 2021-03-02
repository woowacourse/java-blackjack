package car;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private final static List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (Car car : cars) {
            report.append(String.format("%s : %d리터" + "\n", car.getName(), (int) car.getChargeQuantity()));
        }
        return report.toString();
    }

    //                "Sonata : 15리터" + NEWLINE +
//            "K5 : 20리터" + NEWLINE +
//            "Sonata : 12리터" + NEWLINE +
//            "Avante : 20리터" + NEWLINE +
//            "K5 : 30리터" + NEWLINE
    public void addCar(final Car car) {
        cars.add(car);
    }
}
