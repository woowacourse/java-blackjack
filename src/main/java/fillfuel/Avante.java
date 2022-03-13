package fillfuel;

public class Avante extends Car {

	private static final String NAME = "Avante";
	private static final int DISTANCE_PER_LITER = 15;

	public Avante() {
		this(0);
	}

	public Avante(int distance) {
		super(NAME, DISTANCE_PER_LITER, distance);
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

	@Override
	double getChargeQuantity() {
		return super.getChargeQuantity();
	}
}
