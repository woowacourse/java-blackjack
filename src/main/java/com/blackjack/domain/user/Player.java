package com.blackjack.domain.user;

import com.blackjack.domain.Score;

public class Player extends User {
	private static final int DRAW_CONDITION = 20;

	Player(Name name) {
		super(name);
	}

	@Override
	public boolean canDraw() {
		Score score = hands.calculateScore();
		return score.isLowerThan(DRAW_CONDITION);
	}
}
