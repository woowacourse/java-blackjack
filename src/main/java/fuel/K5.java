package fuel;

public class K5 extends Car {
	private final double distance;
	private static final double DENOMINATOR = 13.0;
	private static final String name = "K5";

	public K5(int distance) {
		this.distance = distance;
	}

	@Override
	double getDistancePerLiter() {
		return this.distance / DENOMINATOR;
	}

	@Override
	double getTripDistance() {
		return this.distance;
	}

	@Override
	String getName() {
		return this.name;
	}
}
