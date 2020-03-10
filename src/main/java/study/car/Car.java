package study.car;

public class Car implements ChargeQuantity {
	protected String name;
	protected double distancePerLiter;
	protected double tripDistance;

	protected double getDistancePerLiter() {
		return distancePerLiter;
	}

	protected double getTripDistance() {
		return tripDistance;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getChargeQuantity() {
		return getTripDistance() / getDistancePerLiter();
	}
}
