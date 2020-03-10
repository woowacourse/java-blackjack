package rentacar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private List<Car> cars;

    public RentCompany(List<Car> cars) {
        this.cars = cars;
    }

    public static RentCompany create() {
        List<Car> cars = new ArrayList<>();
        return new RentCompany(cars);
    }

    public void addCar(Car car){
        return;
    }

    public String generateReport() {
        return null;
    }
}
