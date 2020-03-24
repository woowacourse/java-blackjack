package domain.gamer;

import exception.InvalidMoneyException;

public class Money {
	public static final Money ZERO = new Money(0);

	private final double money;

	private Money(double money) {
		validate(money);
		this.money = money;
	}

	private void validate(double money) {
		if (money < 0) {
			throw new InvalidMoneyException(String.valueOf(money));
		}
	}

	public static Money of(double money) {
		return new Money(money);
	}

	public static Money of(String money) {
		return of(stringToDouble(money));
	}

	private static double stringToDouble(String money) {
		try {
			return Double.parseDouble(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(String.format("%s 는 잘못된 형태의 입니다.", money));
		}
	}

	public double multiply(double ratio) {
		return this.money * ratio;
	}
}
