package domain.card;

public record Score(int value) {

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Score score = (Score)o;

		return value == score.value;
	}

}
