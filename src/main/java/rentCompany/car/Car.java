package rentCompany.car;

public abstract class Car {
	private final double distance;
	private final double distancePerLiter;
	private final String name;

	protected Car(double distance, double distancePerLiter, String name) {
		this.distance = distance;
		this.distancePerLiter = distancePerLiter;
		this.name = name;
	}

	protected double getDistancePerLiter() {
		return distancePerLiter;
	}

	protected double getDistance() {
		return distance;
	}

	public String getName() {
		return name;
	}

	public double getChargeQuantity() {
		return getDistance() / getDistancePerLiter();
	}
}
