package blackjack.domain.game;

import java.util.Collection;
import java.util.Objects;

public final class Money {

	private static final int NEGATIVE = -1;
	private static final int ZERO = 0;

	private final double value;

	public Money(final double money) {
		this.value = money;
	}

	public static Money sumOf(final Collection<Money> monies) {
		return new Money(sum(monies));
	}

	private static double sum(Collection<Money> monies) {
		return monies.stream()
				.mapToDouble(Money::getValue)
				.sum();
	}

	public Money multiply(final double value) {
		double afterMultiply = this.value * (value);
		return new Money(afterMultiply);
	}

	public double getMinusValue() {
		return value * NEGATIVE;
	}

	public double getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Money money = (Money) o;
		return Double.compare(money.value, value) == ZERO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
