package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String COLON = " : ";
    private static final String FUEL_UNIT = "리터";

    private final List<AbstractCar> abstractCars;

    private RentCompany() {
        this.abstractCars = new ArrayList<>();
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public void addCar(AbstractCar abstractCar) {
        abstractCars.add(abstractCar);
    }

    public String generateReport() {
        StringBuilder stringBuilder = new StringBuilder();
        abstractCars.forEach(
                abstractCar -> stringBuilder.append(abstractCar.getName()).append(COLON).append((int) abstractCar.getChargeQuantity())
                .append(FUEL_UNIT).append(NEWLINE));
        return stringBuilder.toString();
    }
}
