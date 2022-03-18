package blackjack.domain.user;

import java.util.Objects;

public class Money {
	private final double money;

	public Money(double money) {
		this.money= money;
	}

	public double getMoney() {
		return money;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money1 = (Money)o;
		return Double.compare(money1.money, money) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}

	public Money multiply(double value) {
		return new Money(this.money * value);
	}
}
