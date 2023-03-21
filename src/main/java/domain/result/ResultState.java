package domain.result;

public enum ResultState {

	BLACKJACK(1.5),
	WIN(1),
	DRAW(0),
	DEFEAT(-1);

	private final double multiplier;

	ResultState(double multiplier) {
		this.multiplier = multiplier;
	}

	public double getMultiplier() {
		return multiplier;
	}
}
