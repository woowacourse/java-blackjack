package blackjack.domain.game;

import java.math.BigDecimal;
import java.util.Collection;

public class Money {

	private final BigDecimal value;

	public Money(String money) {
		this.value = new BigDecimal(money);
	}

	private Money(BigDecimal money) {
		this.value = money;
	}

	public static Money sumOf(Collection<Money> monies) {
		BigDecimal value = monies.stream()
				.map(Money::getValue)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return new Money(value);
	}

	public Money multiply(BigDecimal value) {
		BigDecimal afterMultiply = this.value.multiply(value);
		return new Money(afterMultiply);
	}

	public Money getMinusValue() {
		return new Money(value.negate());
	}

	public BigDecimal getValue() {
		return value;
	}
}
