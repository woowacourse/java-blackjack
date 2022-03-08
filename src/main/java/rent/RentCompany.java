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
        StringBuilder stringBuilder = new StringBuilder();

        for (Car car : carList) {
            String name = car.getName();
            long chargeQuantity = Math.round(car.getChargeQuantity());

            stringBuilder.append(name);
            stringBuilder.append(" : ");
            stringBuilder.append(chargeQuantity);
            stringBuilder.append("리터");
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
