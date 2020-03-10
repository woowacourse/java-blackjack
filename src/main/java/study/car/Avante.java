package study.car;

public class Avante extends Car {
	public Avante(int tripDistance) {
		this.name = "Avante";
		this.distancePerLiter = 15;
		this.tripDistance = tripDistance;
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

	@Override
	double getChargeQuantity() {
		return super.getChargeQuantity();
	}
}
