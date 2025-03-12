package domain.card;

public class Count {
	private final int value;

	public Count(final int value) {
		this.value = value;
	}

	public Count increment() {
		return new Count(value + 1);
	}

	public Count decrement() {
		return new Count(value - 1);
	}

	public boolean isZero() {
		return value == 0;
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

		Count count = (Count)o;

		return value == count.value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	public boolean isGreaterThan(final Count value) {
		return false;
	}
}
