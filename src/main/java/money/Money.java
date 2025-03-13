package money;

public class Money {
	private final int value;

	public Money(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public Money multiply(double scale) {
		return new Money((int)(value * scale));
	}
}
