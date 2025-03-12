package domain.card;

public class Score {
	private final int value;

	public Score(final int value) {
		this.value = value;
	}

	public Score plus(final int plusValue) {
		return new Score(value + plusValue);
	}

	public int getValue() {
		return value;
	}
}
