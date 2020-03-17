package domain.user;

import java.util.Objects;

public class Money {
	private static final double BLACKJACK_ADDITIONAL_RATE = 1.5;
	private static final int MIN = 0;
	private static final String WRONG_MONEY_MESSAGE = "최소 금액보다 작습니다.";
	private static final String NOT_INTEGER_MESSAGE = "숫자가 아닙니다.";

	private final int money;

	public Money(int money) {
		this(String.valueOf(money));
	}

	public Money(String money) {
		validateInteger(Objects.requireNonNull(money));
		this.money = Integer.parseInt(money);
		validateExceedMinMoney(this.money);
	}

	public void validateInteger(String money) {
		try {
			Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(NOT_INTEGER_MESSAGE);
		}
	}

	public void validateExceedMinMoney(int money) {
		if (money <= MIN) {
			throw new IllegalArgumentException(WRONG_MONEY_MESSAGE);
		}
	}

	public Money win() {
		return new Money(money + money);
	}

	public Money blackjackWin() {
		return new Money(money + (int)(money * BLACKJACK_ADDITIONAL_RATE));
	}

	public int compare(Money money) {
		return money.calculate(this.money);
	}

	private int calculate(int money) {
		return this.money - money;
	}

	public int getMoney() {
		return money;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money1 = (Money)o;
		return money == money1.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
