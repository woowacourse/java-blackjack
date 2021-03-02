package rentcar;

public class Sonata extends Car {
	public Sonata(final double tripDistance) {
		super(tripDistance);
	}

	@Override
	double getDistancePerLiter() {
		return 10;
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
