package domain;

public class BettingMoney {
	private static final int MIN_MONEY = 0;

	private final int bettingMoney;

	private BettingMoney(int bettingMoney) {
		if (bettingMoney <= MIN_MONEY) {
			throw new IllegalArgumentException("베팅 금액은 최소 1원 이상이어야 합니다.");
		}
		this.bettingMoney = bettingMoney;
	}

	public static BettingMoney of(int bettingMoney) {
		return new BettingMoney(bettingMoney);
	}

	public static BettingMoney of(String bettingMoney) {
		return new BettingMoney(Integer.parseInt(bettingMoney));
	}

	public int intValue() {
		return bettingMoney;
	}
}
