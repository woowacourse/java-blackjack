package study.car;

public class Sonata extends Car {
	public Sonata(int tripDistance) {
		this.name = "Sonata";
		this.distancePerLiter = 10;
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
