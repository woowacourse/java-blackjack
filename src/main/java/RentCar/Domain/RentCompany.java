package RentCar.Domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    List<Car> cars = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder generateReport = new StringBuilder();
        for(Car car : cars) {
            generateReport.append(car.getName());
            generateReport.append(" : ");
            generateReport.append(car.getAmountOfOil());
            generateReport.append("리터\n");
        }
        return generateReport.toString();
    }
}
