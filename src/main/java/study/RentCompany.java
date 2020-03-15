package study;

import java.util.ArrayList;
import java.util.List;

import study.car.ChargeQuantity;

public class RentCompany {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final List<ChargeQuantity> cars;

	private RentCompany() {
		this.cars = new ArrayList<>();
	}

	public static RentCompany create() {
		return new RentCompany();
	}

	public void addCar(ChargeQuantity car) {
		cars.add(car);
	}

	public List<ChargeQuantity> getCars() {
		return cars;
	}

	public String generateReport() {
		StringBuilder carsReport = new StringBuilder();
		for (ChargeQuantity car : cars) {
			carsReport.append(car.getName())
				.append(" : ")
				.append((int)car.getChargeQuantity())
				.append("리터");
			carsReport.append(NEWLINE);
		}
		return carsReport.toString();
	}
}
