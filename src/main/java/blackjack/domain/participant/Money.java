package blackjack.domain.participant;

import java.util.Objects;

public class Money {
	private final double money;

	public Money(double money) {
		this.money = money;
	}

	public static Money of(double money) {
		return new Money(money);
	}

	public double multiplyMoneyWithOperation(double operation) {
		return money * operation;
	}

	public int getMoney() {
		return (int)money;
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
}
