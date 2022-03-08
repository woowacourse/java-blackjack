package fillfuel;

public class Sonata extends Car {

	private static final String NAME = "Sonata";
	private static final int DISTANCE_PER_LITER = 10;

	public Sonata() {
		this(0);
	}

	public Sonata(int distance) {
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
