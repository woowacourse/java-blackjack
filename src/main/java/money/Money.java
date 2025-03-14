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

	public Money minus(final Money minusMoney) {
		return new Money(value - minusMoney.value);
	}

	public Money plus(final Money plusMoney) {
		return new Money(value + plusMoney.value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Money money = (Money)o;

		return value == money.value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
