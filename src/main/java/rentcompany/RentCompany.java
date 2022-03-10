package rentcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {

    private List<Car> cars;

    private RentCompany() {
        cars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public String generateReport() {
        return cars.stream()
                .map(car -> car.getName() + " : " + car.getChargeQuantity() + "리터")
                .collect(Collectors.joining("\n"));
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
