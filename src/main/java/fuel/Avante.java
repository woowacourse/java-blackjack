package fuel;

public class Avante extends Car {
	private final double distance;
	private static final double DENOMINATOR = 15.0;
	private static final String name = "Avante";

	public Avante(int distance) {
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
