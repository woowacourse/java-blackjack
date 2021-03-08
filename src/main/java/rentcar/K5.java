package rentcar;

public class K5 extends Car {
	public K5(double tripDistance) {
		super(tripDistance);
	}

	@Override
	double getDistancePerLiter() {
		return 13;
	}

	@Override
	String getName() {
		return "K5";
	}
}
