package fuel;

public class K5 extends Car{
	private final String name = "K5";
	private final double tripDistance;
	private final double distancePerLitter = 13;

	public K5(double tripDistance) {
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
