package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private final List<Car> rentCars;

    private RentCompany() {
        this.rentCars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        rentCars.add(car);
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        for (Car rentCar : rentCars) {
            sb.append(rentCar.getName() + " : " + (int) (rentCar.getChargeQuantity()) + "리터").append("\n");
        }

        return sb.toString();
    }
}
