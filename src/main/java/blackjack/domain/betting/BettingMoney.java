package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.BettingMoneyException;

import java.util.Objects;

public final class BettingMoney {
	public static final int ZERO = 0;
	public static final double BLACKJACK_BONUS_MULTIPLE = 1.5;

	private final double amount;

	private BettingMoney(double amount) {
		validateMoneyIsPositiveOrZero(amount);
		this.amount = amount;
	}

	private void validateMoneyIsPositiveOrZero(double amount) {
		if (amount < ZERO) {
			throw new BettingMoneyException("배팅 금액은 0 이하일 수 없습니다.");
		}
	}

	public static BettingMoney zero() {
		return new BettingMoney(ZERO);
	}

	public static BettingMoney of(String amount) {
		validateMoneyIsNotNull(amount);

		try {
			return new BettingMoney(Double.parseDouble(amount));
		} catch (NumberFormatException e) {
			throw new BettingMoneyException("올바른 값을 입력하세요.");
		}
	}

	private static void validateMoneyIsNotNull(String amount) {
		if (amount == null) {
			throw new BettingMoneyException("Null 값이 입력되었습니다.");
		}
	}

	public BettingMoney computeWinningAmountWithBlackjack() {
		return new BettingMoney(amount * BLACKJACK_BONUS_MULTIPLE);
	}

	public BettingMoney computeSimpleWinningAmount() {
		return new BettingMoney(amount);
	}

	public BettingMoney add(BettingMoney other) {
		return new BettingMoney(amount + other.amount);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BettingMoney that = (BettingMoney) o;
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
