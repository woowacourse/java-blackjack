package com.blackjack.domain.user;

import java.util.Objects;

import com.blackjack.domain.BettingMoney;
import com.blackjack.domain.Score;

public class Player extends User {
	private static final int DRAW_CONDITION = 21;

	private final BettingMoney bettingMoney;

	public Player(String name, int bettingMoney) {
		this(new Name(name), new BettingMoney(bettingMoney));
	}

	private Player(Name name, BettingMoney bettingMoney) {
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
		Score score = hand.calculateScore();
		return score.isNotBust() && score.isLowerThan(DRAW_CONDITION);
	}

	public int calculateProfit(double profitRate) {
		return bettingMoney.calculateProfit(profitRate);
	}
}
