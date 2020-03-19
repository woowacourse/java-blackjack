package com.blackjack.domain.user;

import com.blackjack.domain.BettingMoney;
import com.blackjack.domain.Score;

public class Player extends User {
	private static final int DRAW_CONDITION = 21;
	private static final int DEFAULT_BETTING_MONEY = 1_000;

	Player(String name, int bettingMoney) {
		this(new Name(name), new BettingMoney(bettingMoney));
	}

	Player(Name name) {
		this(name, new BettingMoney(DEFAULT_BETTING_MONEY));
	}

	Player(Name name, BettingMoney bettingMoney) {
		super(name);
	}

	@Override
	public boolean canDraw() {
		Score score = hands.calculateScore();
		return !score.isBust() && score.isLowerThan(DRAW_CONDITION);
	}
}
