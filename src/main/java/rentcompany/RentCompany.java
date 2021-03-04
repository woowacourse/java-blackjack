package rentcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static String STRING_FORMAT = "%s : %.0f리터\n";

    private List<Car> cars = new ArrayList<>();

    public static void main(String[] args) {

    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        cars.forEach(item -> sb.append(String.format(STRING_FORMAT, item.getName(), item.getChargeQuantity())));
        return sb.toString();
    }
}
