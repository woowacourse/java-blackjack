package domain.user;

import java.util.Objects;

public class Money {
	private static final int MINIMUM_BETTING_MONEY = 1;
	private static final int DEALER_PRIZE_FACTOR = -1;
	private static final String NOT_ENOUGH_BETTING_MONEY_EXCEPTION_MESSAGE = "해당 금액으로는 배팅하실 수 없습니다.";

	private final int money;

	private Money(int money) {
		validate(money);
		this.money = money;
	}

	public static Money of(int money) {
		return new Money(money);
	}

	private void validate(int money) {
		if (money < MINIMUM_BETTING_MONEY) {
			throw new IllegalArgumentException(NOT_ENOUGH_BETTING_MONEY_EXCEPTION_MESSAGE);
		}
	}

	public int multiply(double prizeFactor) {
		return (int)(money * prizeFactor);
	}

	public static int calculateDealerPrize(int playerPrize) {
		return playerPrize * DEALER_PRIZE_FACTOR;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Money that = (Money)object;
		return this.money == that.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
