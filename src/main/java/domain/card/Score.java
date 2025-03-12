package domain.card;

public class Score {
	private final int value;

	public Score(final int value) {
		this.value = value;
	}

	public Score plus(final Score plusValue) {
		return new Score(value + plusValue.value);
	}

	public Score minus(final Score minusValue) {
		return new Score(value - minusValue.value);
	}

	public boolean isGreaterThan(final Score score) {
		return this.value > score.value;
	}

	public boolean isLessThan(final Score score) {
		return this.value < score.value;
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
