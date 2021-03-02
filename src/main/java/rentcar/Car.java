package rentcar;

public abstract class Car {
	protected final double tripDistance;

	protected Car(final double tripDistance) {
		this.tripDistance = tripDistance;
	}

	abstract double getDistancePerLiter();

	abstract double getTripDistance();

	abstract String getName();

	public double getChargeQuantity() {
		return getTripDistance() / getDistancePerLiter();
	}
}
