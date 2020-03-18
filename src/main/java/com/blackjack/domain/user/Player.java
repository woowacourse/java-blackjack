package com.blackjack.domain.user;

import com.blackjack.domain.Score;

public class Player extends User {
	private static final int DRAW_CONDITION = 21;

	Player(Name name) {
		super(name);
	}

	@Override
	public boolean canDraw() {
		Score score = hand.calculate();
		return !score.isBust() && score.isLowerThan(DRAW_CONDITION);
	}
}
