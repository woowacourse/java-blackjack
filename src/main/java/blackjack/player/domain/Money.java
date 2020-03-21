package blackjack.player.domain;

import java.util.Objects;

public class Money {
	private static final int NEGATIVE_NUMBER = -1;
	private final long amount;

	private Money(long amount) {
		this.amount = amount;
	}

	public static Money create(String inputMoney) {
		checkEmpty(inputMoney);
		try {
			return create(Long.parseLong(inputMoney));
		} catch (ArithmeticException e) {
			throw new IllegalArgumentException("숫자를 입력해야합니다.");
		}
	}

	private static void checkEmpty(String input) {
		if (input == null || input.trim().isEmpty()) {
			throw new IllegalArgumentException("돈의 입력이 필요합니다");
		}
	}

	public static Money create(long money) {
		return new Money(money);
	}

	public static Money sum(Money money, Money other) {
		return create(money.amount + other.amount);
	}

	public Money multiply(double profit) {
		return create((long)((double)amount * profit));
	}

	public boolean isLessThan(int minimumBettingMoney) {
		return amount < minimumBettingMoney;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money = (Money)o;
		return amount == money.amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	public long getAmount() {
		return amount;
	}

	public Money calculateNegative() {
		return create(this.amount * NEGATIVE_NUMBER);
	}
}
