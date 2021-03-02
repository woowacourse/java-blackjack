package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private final List<Car> cars = new ArrayList<>();
    
    public static RentCompany create() {
        return new RentCompany();
    }
    
//    public void addCar(RentCompany rentCompany) {
//
//    }

    public String generateReport() {
        return "0";
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
