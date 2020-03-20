package com.blackjack.domain.user;

import java.util.Objects;

import com.blackjack.domain.BettingMoney;
import com.blackjack.domain.ResultType;
import com.blackjack.domain.Score;

public class Player extends User {
	private static final int DRAW_CONDITION = 21;
	private static final int DEFAULT_BETTING_MONEY = 1_000;

	private final BettingMoney bettingMoney;

	Player(String name, int bettingMoney) {
		this(new Name(name), new BettingMoney(bettingMoney));
	}

	public Player(Name name) {
		this(name, new BettingMoney(DEFAULT_BETTING_MONEY));
	}

	Player(Name name, BettingMoney bettingMoney) {
		super(name);
		validateBettingMoney(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	private void validateBettingMoney(BettingMoney bettingMoney) {
		if (Objects.isNull(bettingMoney)) {
			throw new IllegalArgumentException("베팅 금액이 존재하지 않습니다.");
		}
	}

	@Override
	public boolean canDraw() {
		Score score = hand.calculate();
		return !score.isBust() && score.isLowerThan(DRAW_CONDITION);
	}

	public int calculateProfit(ResultType result) {
		return bettingMoney.calculateProfit(result.getProfitRate());
	}
}
