package fillfuel;

public class K5 extends Car {

	private static final String NAME = "K5";
	private static final int DISTANCE_PER_LITER = 13;

	public K5() {
		this(0);
	}

	public K5(int distance) {
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