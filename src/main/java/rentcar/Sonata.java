package rentcar;

public class Sonata extends Car {
	public Sonata(final double tripDistance) {
		super(tripDistance);
	}

	@Override
	double getDistancePerLiter() {
		return 10f;
	}

	@Override
	double getTripDistance() {
		return tripDistance;
	}

	@Override
	String getName() {
		return "Sonata";
	}
}
