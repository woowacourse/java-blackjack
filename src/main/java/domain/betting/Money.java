package domain.betting;

import java.util.Objects;

public class Money {
	private static final int MIN = 0;
	private static final String WRONG_MONEY_MESSAGE = "최소 금액보다 작습니다.";
	private static final String NOT_INTEGER_MESSAGE = "숫자가 아닙니다.";

	private final int money;

	private Money(int money) {
		this(String.valueOf(money));
	}

	private Money(String money) {
		validateInteger(Objects.requireNonNull(money));
		this.money = Integer.parseInt(money);
		validateExceedMinMoney(this.money);
	}

	public static Money from(String money) {
		return new Money(money);
	}

	public static Money from(int money) {
		return new Money(money);
	}

	public void validateInteger(String money) {
		try {
			Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(NOT_INTEGER_MESSAGE);
		}
	}

	public void validateExceedMinMoney(int money) {
		if (money < MIN) {
			throw new IllegalArgumentException(WRONG_MONEY_MESSAGE);
		}
	}

	public int calculateProfit(double profitRate) {
		return (int)(money * profitRate);
	}

	public int calculateDifferent(int money) {
		return money - this.money;
	}

}