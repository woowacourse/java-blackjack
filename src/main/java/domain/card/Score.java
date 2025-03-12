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

	public boolean isGreaterThan(final Score score) {
		return this.value > score.value;
	}

	public boolean isLessThan(final int value) {
		return this.value < value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Score score = (Score)o;

		return value == score.value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
