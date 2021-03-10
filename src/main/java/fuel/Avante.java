package fuel;

public class Avante extends Car {
	private final double distancePerLiter;
	private final String name;

	public Avante(double tripDistance) {
		super(tripDistance);
		this.distancePerLiter = 15;
		this.name = "Avante";
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
