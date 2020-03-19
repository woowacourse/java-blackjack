package blackjack.domain.result;

import java.util.Objects;

import blackjack.domain.exceptions.InvalidBettingMoneyException;

public class BettingMoney {
	public static final BettingMoney ZERO = new BettingMoney(0);
	private final int bettingMoney;

	public BettingMoney(int bettingMoney) {
		this.bettingMoney = bettingMoney;
	}

	public static BettingMoney valueOf(String bettingMoney) {
		return new BettingMoney(parseToInt(bettingMoney));
	}

	private static int parseToInt(String bettingMoney) {
		try {
			int parsedInt = Integer.parseInt(bettingMoney);
			validate(parsedInt);
			return parsedInt;
		} catch (NumberFormatException e) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NOT_INTEGER);
		}
	}

	private static void validate(int bettingMoney) {
		if (bettingMoney < 0) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.INVALID);
		}
	}

	public BettingMoney multiply(Double bettingRate) {
		return new BettingMoney((int)(this.bettingMoney * bettingRate));
	}

	public int getBettingMoney() {
		return bettingMoney;
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
