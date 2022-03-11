package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

    private static final String CAR_REPORT_FORMAT = "%s : %d리터" + System.lineSeparator();
    private final List<AbstractCar> abstractCars;

    private RentCompany() {
        abstractCars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(AbstractCar abstractCar) {
        abstractCars.add(abstractCar);
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (AbstractCar abstractCar : abstractCars) {
            stringBuilder.append(String.format(CAR_REPORT_FORMAT, abstractCar.getName(), (int) abstractCar.getChargeQuantity()));
        }
        return stringBuilder.toString();
    }
}
