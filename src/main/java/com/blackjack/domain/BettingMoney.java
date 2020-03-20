package com.blackjack.domain;

public class BettingMoney {
	private static final int MIN_MONEY = 1_000;
	private static final int MAX_MONEY = 200_000_000;
	private static final int MONEY_UNIT = 1_000;

	private final int money;

	public BettingMoney(int money) {
		validateMoney(money);
		this.money = money;
	}

	private void validateMoney(int money) {
		validateBounds(money);
		validateUnit(money);
	}

	private void validateUnit(int money) {
		if (money % MONEY_UNIT != 0) {
			throw new IllegalArgumentException("베팅액의 단위가 올바르지 않습니다.");
		}
	}

	private void validateBounds(int money) {
		if (money < MIN_MONEY || money > MAX_MONEY) {
			throw new IllegalArgumentException("베팅액의 범위를 벗어났습니다.");
		}
	}

	public int calculateProfit(double profitRate) {
		return (int)(money * profitRate);
	}
}
