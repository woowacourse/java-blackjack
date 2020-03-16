package mission.car;

public class Avante extends Car {
	private static final int DISTANCE_PER_LITER = 15;
	private static final String NAME = "Avante";

	public Avante(int distance) {
		super(distance);
	}

	@Override
	double getDistancePerLiter() {
		return DISTANCE_PER_LITER;
	}

	@Override
	double getTripDistance() {
		return distance;
	}

	@Override
	String getName() {
		return NAME;
	}
}
