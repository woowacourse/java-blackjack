package fuel;

public class K5 extends Car {
	private final double distancePerLiter;
	private final String name;

	public K5(double tripDistance) {
		super(tripDistance);
		this.distancePerLiter = 13;
		this.name = "K5";
	}

	@Override
	double getDistancePerLiter() {
		return this.distancePerLiter;
	}

	@Override
	double getTripDistance() {
		return this.tripDistance;
	}

	@Override
	String getName() {
		return this.name;
	}
}
