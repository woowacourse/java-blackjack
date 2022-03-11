package rantcompany;

public class K5 extends Car {

	private final String name;
	private final int tripDistance;
	private final int fuelEfficiency;

	public K5(int tripDistance) {
		this.name = "K5";
		this.tripDistance = tripDistance;
		this.fuelEfficiency = 13;
	}

	@Override
	double getDistancePerLiter() {
		return fuelEfficiency;
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
