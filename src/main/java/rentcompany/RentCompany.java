package rentcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private final List<Car> cars;

    private RentCompany() {
        this.cars = new ArrayList<>();

    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();

        cars.forEach(car -> stringBuilder.append(car.getName())
            .append(" : ")
            .append(Math.round(car.getChargeQuantity()))
            .append("리터\n"));

        return stringBuilder.toString();
    }

}
