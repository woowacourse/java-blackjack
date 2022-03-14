package fuel;

public class Sonata extends Car{
	private final String name = "Sonata";
	private final double tripDistance;
	private final double distancePerLitter = 10;

	public Sonata(double tripDistance) {
		validateDistance(tripDistance);
		this.tripDistance = tripDistance;
	}

	private void validateDistance(double distance) {
		if (distance < 0) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	double getDistancePerLiter() {
		return distancePerLitter;
	}

	@Override
	double getTripDistance() {
		return tripDistance;
	}

	@Override
	String getName() {
		return this.name;
	}
}
