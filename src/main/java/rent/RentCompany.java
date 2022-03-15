package rent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import rent.car.Car;

public class RentCompany {

    private final List<Car> carList = new ArrayList<>();

    private RentCompany() {
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        carList.add(car);
    }

    public String generateReport() {
        return carList.stream()
                .map(this::formatName)
                .collect(Collectors.joining());
    }

    private String formatName(Car car) {
        return String.format(
                "%s : %d리터\n",
                car.getName(),
                Math.round(car.getChargeQuantity())
        );
    }
}
