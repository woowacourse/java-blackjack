package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.MoneyException;
import blackjack.domain.user.Result;

import java.util.Objects;

public final class Money {
	public static final int ZERO = 0;
	public static final int MINUS_ZERO = -0;

	private final double amount;

	private Money(double amount) {
		if (amount == MINUS_ZERO) {
			amount = ZERO;
		}
		this.amount = amount;
	}

	public static Money zero() {
		return new Money(ZERO);
	}

	public static Money of(String amount) {
		validateMoneyIsNotNull(amount);

		try {
			return new Money(Double.parseDouble(amount));
		} catch (NumberFormatException e) {
			throw new MoneyException("올바른 값을 입력하세요.");
		}
	}

	private static void validateMoneyIsNotNull(String amount) {
		if (amount == null) {
			throw new MoneyException("Null 값이 입력되었습니다.");
		}
	}

	public Money computeResultingAmount(Result result) {
		return new Money(result.computeResultAmount(amount));
	}

	public Money add(Money other) {
		return new Money(amount + other.amount);
	}

	public Money minus() {
		return new Money(-amount);
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Money that = (Money) o;
		return Double.compare(that.amount, amount) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public String toString() {
		return "BettingMoney{" +
				"amount=" + amount +
				'}';
	}
}
