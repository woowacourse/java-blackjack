package fuel;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {
	private final List<Car> cars;

	private RentCompany() {
		cars = new ArrayList<>();
	}

	public static RentCompany create() {
		return new RentCompany();
	}

	public void addCar(Car car) {
		this.cars.add(car);
	}

	public String generateReport() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Car car : cars) {
			stringBuilder.append(car.getName() + " : " + (int)car.getDistancePerLiter() + "리터\n");
		}
		return stringBuilder.toString();
	}
}
