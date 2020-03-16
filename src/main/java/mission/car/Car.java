package mission.car;

public abstract class Car {
	private static final String NEWLINE = System.getProperty("line.separator");
	protected final int distance;

	public Car(int distance) {
		this.distance = distance;
	}

	/**
	 * 리터당 이동 거리. 즉, 연비
	 */
	abstract double getDistancePerLiter();

	/**
	 * 여행하려는 거리
	 */
	abstract double getTripDistance();

	/**
	 * 차종의 이름
	 */
	abstract String getName();

	/**
	 * 주입해야할 연료량을 구한다.
	 */
	double getChargeQuantity() {
		return getTripDistance() / getDistancePerLiter();
	}

	public String getReport() {
		return String.format("%s : %.0f리터%s", getName(), getChargeQuantity(), NEWLINE);
	}
}