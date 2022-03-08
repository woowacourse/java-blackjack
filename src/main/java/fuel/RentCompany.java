package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final List<Car> cars;

	private RentCompany(List<Car> cars) { this.cars = cars;}

	public static RentCompany create() {
		return new RentCompany(new ArrayList<>());
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public String generateReport() {
		StringBuilder builder = new StringBuilder();
		for (Car car : cars) {
			builder.append(car.getName() + " : " + (int) car.getChargeQuantity() + "리터" + NEWLINE);
		}
		return builder.toString();
	}
}
