package rantcompany;

import java.util.ArrayList;
import java.util.List;

public class RentCompany {

	private static final String NEWLINE = System.getProperty("line.separator");

	List<Car> cars;

	public RentCompany(List<Car> cars) {
		this.cars = cars;
	}

	public static RentCompany create() {
		return new RentCompany(new ArrayList<>());
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public String generateReport() {
		StringBuilder report = new StringBuilder();
		for (Car car : cars) {
			report.append(generateCarReport(car));
		}
		return report.toString();
	}

	public String generateCarReport(Car car) {
		return String.format("%s : %d리터" + NEWLINE, car.getName(), (int)car.getChargeQuantity());
	}

}
