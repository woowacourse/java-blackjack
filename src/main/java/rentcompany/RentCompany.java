package rentcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    
    private static final String NEWLINE = System.getProperty("line.separator");
    
    private final List<Car> cars;
    
    public RentCompany() {
        this(new ArrayList<>());
    }
    
    public RentCompany(List<Car> cars) {
        this.cars = cars;
    }
    
    public static RentCompany create() {
        return new RentCompany();
    }
    
    public void addCar(Car car) {
        cars.add(car);
    }
    
    public String generateReport() {
        StringBuilder reportBuilder = new StringBuilder();
        
        for (Car car : cars) {
            reportBuilder.append(car.getName())
                         .append(" : ")
                         .append((int) car.getChargeQuantity())
                         .append("리터")
                         .append(NEWLINE);
        }
        
        return reportBuilder.toString();
    }
}
