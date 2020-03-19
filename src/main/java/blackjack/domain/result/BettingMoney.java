package blackjack.domain.result;

import java.util.Objects;

import blackjack.domain.exceptions.InvalidBettingMoneyException;

public class BettingMoney {
	private final int bettingMoney;

	public BettingMoney(int bettingMoney) {
		validate(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	public static BettingMoney valueOf(String bettingMoney) {
		return new BettingMoney(parseToInt(bettingMoney));
	}

	private void validate(int bettingMoney) {
		if (bettingMoney < 0) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.INVALID);
		}
	}

	private static int parseToInt(String bettingMoney) {
		try {
			return Integer.parseInt(bettingMoney);
		} catch (NumberFormatException e) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NOT_INTEGER);
		}
	}

	public BettingMoney multiply(Double bettingRate) {
		return new BettingMoney((int)(this.bettingMoney * bettingRate));
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		BettingMoney that = (BettingMoney)object;
		return bettingMoney == that.bettingMoney;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bettingMoney);
	}
}
