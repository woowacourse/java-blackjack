package rentcar;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
	public static final String NEWLINE = System.getProperty("line.separator");
	private static final List<Car> cars = new ArrayList<>();

	private RentCompany() {
	}

	public static RentCompany create() {
		return new RentCompany();
	}

	public void addCar(final Car car) {
		cars.add(car);
	}

	public String generateReport() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Car car : cars) {
			stringBuilder.append(car.getSummary())
					.append(NEWLINE);
		}
		return stringBuilder.toString();
	}
}
