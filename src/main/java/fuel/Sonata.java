package fuel;

public class Sonata extends Car {
	private final double distance;
	private static final double DENOMINATOR = 10.0;
	private static final String name = "Sonata";

	public Sonata(int distance) {
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
