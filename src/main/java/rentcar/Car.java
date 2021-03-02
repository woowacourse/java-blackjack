package rentcar;

public abstract class Car {
	abstract double getDistancePerLiter();

	abstract double getTripDistance();

	abstract String getName();

	public double getChargeQuantity() {
		return getTripDistance() / getDistancePerLiter();
	}
}
