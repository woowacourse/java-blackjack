package mission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mission.car.Car;

public class RentCompany {
	private final List<Car> cars;

	private RentCompany(List<Car> cars) {
		this.cars = cars;
	}

	public static RentCompany create() {
		return new RentCompany(new ArrayList<>());
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public String generateReport() {
		return cars.stream()
			.map(Car::getReport)
			.collect(Collectors.joining());
	}
}
