package domain.card;

public record Count(int value) {

	public Count increment() {
		return new Count(value + 1);
	}

	public Count decrement() {
		return new Count(value - 1);
	}

	public boolean isZero() {
		return value == 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Count count = (Count)o;

		return value == count.value;
	}

	public boolean isGreaterThan(final Count count) {
		return value > count.value;
	}
}
