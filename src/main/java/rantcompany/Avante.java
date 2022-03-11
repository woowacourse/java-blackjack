package rantcompany;

public class Avante extends Car {

	private final String name;
	private final int tripDistance;
	private final int fuelEfficiency;

	public Avante(int tripDistance) {
		this.name = "Avante";
		this.tripDistance = tripDistance;
		this.fuelEfficiency = 15;
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
