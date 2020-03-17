package domain.gamer;

import java.util.Objects;

public class Money {
	private final double money;

	public Money(String money) {
		this(stringToDouble(money));
	}

	public Money(int money) {
		this((double)money);
	}

	public Money(double money) {
		this.money = money;
	}

	private static double stringToDouble(String money) {
		try {
			return Double.parseDouble(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format("%s 는 잘못된 형태의 입니다.", money));
		}
	}

	public Money plus(Money other) {
		return new Money(this.money + other.money);
	}

	public Money multiply(double ratio) {
		return new Money(this.money * ratio);
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
