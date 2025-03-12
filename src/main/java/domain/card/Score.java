package domain.card;

public class Score {
	private final int value;

	public Score(final int value) {
		this.value = value;
	}

	public Score plus(final int plusValue) {
		return new Score(value + plusValue);
	}

	public Score minus(final int minusValue) {
		return new Score(value - minusValue);
	}

	public boolean isGreaterThan(final int value) {
		return this.value > value;
	}

	public int getValue() {
		return value;
	}
}
