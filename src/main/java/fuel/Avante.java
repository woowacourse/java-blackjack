package fuel;

public class Avante extends Car{
	private final String name = "Avante";
	private final double tripDistance;
	private final double distancePerLitter = 15;

	public Avante(double tripDistance) {
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
