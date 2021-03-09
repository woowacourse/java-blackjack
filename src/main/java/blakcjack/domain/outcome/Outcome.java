package blakcjack.domain.outcome;

public enum Outcome {
	BLACKJACK_WIN(1.5f),
	WIN(1f),
	DRAW(0f),
	LOSE(-1f);

	private final float earningRate;

	Outcome(final float earningRate) {
		this.earningRate = earningRate;
	}

	public float getEarningRate() {
		return earningRate;
	}
}
