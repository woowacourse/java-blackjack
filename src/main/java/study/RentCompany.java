package study;

import java.util.ArrayList;
import java.util.List;

import study.car.Car;

public class RentCompany {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final List<Car> cars;

	private RentCompany() {
		this.cars = new ArrayList<>();
	}

	public static RentCompany create() {
		return new RentCompany();
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public List<Car> getCars() {
		return cars;
	}

	public String generateReport() {
		StringBuilder carsReport = new StringBuilder();
		for (Car car : cars) {
			carsReport.append(car);
			carsReport.append(NEWLINE);
		}
		return carsReport.toString();
	}
}
