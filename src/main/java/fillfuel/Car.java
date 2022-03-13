package fillfuel;

import java.util.Objects;

public abstract class Car {

	final String name;
	final int distancePerLiter;
	final int distance;

	public Car(final String name, final int distancePerLiter, final int distance) {
		this.name = name;
		this.distancePerLiter = distancePerLiter;
		this.distance = distance;
	}

	/**
	 * 리터당 이동 거리. 즉, 연비
	 */
	abstract double getDistancePerLiter();

	/**
	 * 여행하려는 거리
	 */
	abstract double getTripDistance();

	/**
	 * 차종의 이름
	 */
	abstract String getName();

	/**
	 * 주입해야할 연료량을 구한다.
	 */
	double getChargeQuantity() {
		return getTripDistance() / getDistancePerLiter();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof Car)) {
			return false;
		}
		Car car = (Car)obj;
		return this.distancePerLiter == car.distancePerLiter
			&& this.name.equals(car.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(distancePerLiter, name);
	}
}