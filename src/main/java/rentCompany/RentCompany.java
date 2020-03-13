package rentCompany;

import rentCompany.car.Car;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
	private static final String NEWLINE = System.getProperty("line.separator");

	private List<Car> cars;

	private RentCompany() {
		cars = new ArrayList<>();
	}

	public static RentCompany create() {
		return new RentCompany();
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public String generateReport() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Car car : cars) {
			stringBuilder.append(car.getName())
					.append(" : ")
					.append(car.getChargeQuantity())
					.append("리터")
					.append(NEWLINE);
		}

		return stringBuilder.toString();
	}
}
