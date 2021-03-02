package rentcompany.domain;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private final List<Car> carList = new ArrayList<>();

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(Car car) {
        carList.add(car);
    }

    public int size(){
        return carList.size();
    }

    public String generateReport() {
        return null;
    }
}
