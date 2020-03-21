package blackjack.domain.result;

import java.util.Objects;

import blackjack.domain.exceptions.InvalidBettingMoneyException;

public class BettingMoney {
	private final int bettingMoney;

	private BettingMoney(int bettingMoney) {
		this.bettingMoney = bettingMoney;
	}

	public static BettingMoney valueOf(int bettingMoney) {
		return new BettingMoney(bettingMoney);
	}

	public static BettingMoney valueOf(String bettingMoney) {
		return BettingMoney.valueOf(parseToInt(bettingMoney));
	}

	private static int parseToInt(String bettingMoney) {
		validate(bettingMoney);
		try {
			int parsedInt = Integer.parseInt(bettingMoney);
			validateNegative(parsedInt);
			return parsedInt;
		} catch (NumberFormatException e) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NOT_INTEGER);
		}
	}

	private static void validate(String bettingMoney) {
		if (Objects.isNull(bettingMoney)) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NULL);
		}
	}

	private static void validateNegative(int bettingMoney) {
		if (bettingMoney < 0) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.INVALID);
		}
	}

	public BettingMoney multiplyBy(Double bettingRate) {
		return BettingMoney.valueOf((int)(this.bettingMoney * bettingRate));
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
