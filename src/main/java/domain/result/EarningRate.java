package domain.result;

public enum EarningRate {
	WIN(1),
	DRAW(0),
	LOSE(-1),
	BLACK_JACK_WIN(1.5);

	private final double earningRate;

	EarningRate(double earningRate) {
		this.earningRate = earningRate;
	}

	public double getEarningRate() {
		return earningRate;
	}
}
