package fuel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentCompany {

    private final Map<Car, Integer> ownedCars;
    private final List<Car> rentCars;

    private RentCompany() {
        this.ownedCars = new HashMap<>();
        this.rentCars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(final Car car) {
        rentCars.add(car);
    }

    public String generateReport() {
        return rentCars.stream()
                .map(this::getCarInfo)
                .collect(Collectors.joining());
    }

    private String getCarInfo(Car car) {
        return car.getName() + " : " + (int)car.getChargeQuantity() + "리터" + "\n";
    }
}
