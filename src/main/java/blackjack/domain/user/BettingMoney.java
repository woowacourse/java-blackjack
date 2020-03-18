package blackjack.domain.user;

import blackjack.domain.user.exceptions.MoneyException;

public class BettingMoney {
	public static final double BLACKJACK_BONUS_MULTIPLE = 1.5;
	private final double amount;

	private BettingMoney(double amount) {
		validateMoneyIsPositive(amount);
		this.amount = amount;
	}

	private void validateMoneyIsPositive(double amount) {
		if (amount <= 0) {
			throw new MoneyException("배팅 금액은 0 이하일 수 없습니다.");
		}
	}

	public static BettingMoney of(String amount) {
		validateMoneyIsNotNull(amount);

		try {
			return new BettingMoney(Double.parseDouble(amount));
		} catch (NumberFormatException e) {
			throw new MoneyException("올바른 값을 입력하세요.");
		}
	}

	private static void validateMoneyIsNotNull(String amount) {
		if (amount == null) {
			throw new MoneyException("Null 값이 입력되었습니다.");
		}
	}

	public BettingMoney computeWinningAmountWtihBlackjack() {
		return new BettingMoney(amount + amount * BLACKJACK_BONUS_MULTIPLE);
	}
}
