package blackjack.domain.result;

import java.util.Objects;

import blackjack.domain.exceptions.InvalidBettingMoneyException;

public class BettingMoney {
	private final int bettingMoney;

	public BettingMoney(int bettingMoney) {
		validate(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	private static void validate(int bettingMoney) {
		if (bettingMoney < 0) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.INVALID);
		}
	}

	public BettingMoney(String bettingMoney) {
		this(parseToInt(bettingMoney));
	}

	private static int parseToInt(String bettingMoney) {
		try {
			validate(bettingMoney);
			return Integer.parseInt(bettingMoney);
		} catch (NumberFormatException e) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NOT_INTEGER);
		}
	}

	private static void validate(String bettingMoney) {
		if (Objects.isNull(bettingMoney)) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.NULL);
		}
	}

	public int multiplyBy(Double bettingRate) {
		return (int)(this.bettingMoney * bettingRate);
	}
}
