package fuel;

public class Sonata extends Car {
	private final double distancePerLiter;
	private final String name;

	public Sonata(double tripDistance) {
		super(tripDistance);
		this.distancePerLiter = 10;
		this.name = "Sonata";
	}

	@Override
	double getDistancePerLiter() {
		return distancePerLiter;
	}

	@Override
	double getTripDistance() {
		return tripDistance;
	}

	@Override
	String getName() {
		return name;
	}
}
