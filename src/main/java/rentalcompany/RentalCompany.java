package rentalcompany;

import java.util.ArrayList;
import java.util.List;
import rentalcompany.car.Car;

public class RentalCompany {
    private final List<Car> cars;
    
    private RentalCompany() {
        this.cars = new ArrayList<>();
    }
    
    public static RentalCompany create() {
        return new RentalCompany();
    }
    
    public void addCar(Car car) {
        cars.add(car);
    }
    
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        for (Car car : cars) {
            report.append(
                    String.format("%s : %1.0f리터" + System.lineSeparator(), car.getName(), car.getChargeQuantity()));
        }
        return report.toString();
    }
}
