package study.car;

public class K5 extends Car {
	public K5(int tripDistance) {
		this.name = "K5";
		this.distancePerLiter = 13;
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
