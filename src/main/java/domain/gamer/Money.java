package domain.gamer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Money {
	public static final Money ZERO = new Money(0);
	private static final int MONEY_THRESHOLD = 0;

	private final double money;

	public Money(double money) {
		if (money < MONEY_THRESHOLD) {
			throw new IllegalArgumentException("Money는 음수가 될 수 없습니다.");
		}
		this.money = money;
	}

	public Money(String money) {
		this(stringToDouble(money));
	}

	public Money(int money) {
		this((double) money);
	}

	private static double stringToDouble(String money) {
		try {
			return Double.parseDouble(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format("%s 는 잘못된 형태의 입니다.", money));
		}
	}

	public static List<Money> list(List<String> bettingMoney) {
		return bettingMoney.stream()
				.map(Money::new)
				.collect(Collectors.toList());
	}

	public double multiply(double ratio) {
		return this.money * ratio;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money1 = (Money) o;
		return Double.compare(money1.money, money) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}