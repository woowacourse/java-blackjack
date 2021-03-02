package rentcar;

public class Avante extends  Car {
	public Avante(double tripDistance) {
		super(tripDistance);
	}

	@Override
	double getDistancePerLiter() {
		return 15;
	}

	@Override
	String getName() {
		return "Avante";
	}
}
