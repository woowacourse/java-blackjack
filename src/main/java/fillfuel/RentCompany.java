package fillfuel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentCompany {

	public static final String NO_REMAINING_CAR_ERROR = "해당_차가_남아있지_않습니다";
	public static final String CAR_MODEL_NOT_EXIST_ERROR = "해당 차가 없습니다.";
	public static final String REPORT_DELIMITER = " : ";
	public static final String LITER = "리터";
	public static final int NONE = 0;
	private final Map<Car, Integer> ownedCars;
	private final List<Car> rentCars;

	private RentCompany(final Map<Car, Integer> cars) {
		this.ownedCars = cars;
		this.rentCars = new ArrayList<>();
	}

	public static RentCompany create() {
		final Map<Car, Integer> cars = new HashMap<>();
		cars.put(new Sonata(), 2);
		cars.put(new K5(), 2);
		cars.put(new Avante(), 1);
		return new RentCompany(cars);
	}

	public void addCar(final Car car) {
		hasCarModel(car);
		isRemainCarModel(car);
		ownedCars.merge(car, -1, Integer::sum);
		rentCars.add(car);
	}

	private void hasCarModel(Car car) {
		if (!ownedCars.containsKey(car)) {
			throw new IllegalArgumentException(CAR_MODEL_NOT_EXIST_ERROR);
		}
	}

	private void isRemainCarModel(Car car) {
		if (ownedCars.get(car) <= NONE) {
			throw new IllegalArgumentException(NO_REMAINING_CAR_ERROR);
		}
	}

	public String generateReport() {
		return rentCars.stream()
			.map(this::getCarReport)
			.collect(Collectors.joining());
	}

	private String getCarReport(Car car) {
		return car.getName() + REPORT_DELIMITER + (int)car.getChargeQuantity() + LITER + "\n";
	}
}
