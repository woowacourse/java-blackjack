package blackjack.player.domain.component;

import java.util.Objects;

public class Money {
	private final long money;

	private Money(long money) {
		Objects.requireNonNull(money, "돈의 입력이 필요합니다.");
		this.money = money;
	}

	public static Money createMoney(String inputMoney) {
		checkEmpty(inputMoney);
		try {
			return createMoney(Long.parseLong(inputMoney));
		} catch (ArithmeticException e) {
			throw new IllegalArgumentException("숫자를 입력해야합니다.");
		}
	}

	private static void checkEmpty(String input) {
		if (input.trim().isEmpty()) {
			throw new IllegalArgumentException("돈의 입력이 필요합니다");
		}
	}

	public static Money createMoney(long money) {
		return new Money(money);
	}

	public Money multiply(double profit) {
		return createMoney((long)((double)money * profit));
	}

	public boolean isLessThan(int minimumBettingMoney) {
		return money < minimumBettingMoney;
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

	public long getMoney() {
		return money;
	}
}
