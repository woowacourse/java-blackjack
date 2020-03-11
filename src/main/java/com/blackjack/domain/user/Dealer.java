package com.blackjack.domain.user;

import com.blackjack.domain.Score;

public class Dealer extends User {
	private static final Score DRAW_CONDITION = new Score(17);

	@Override
	public boolean canDraw() {
		Score score = hands.calculateScore();
		return score.isLowerThan(DRAW_CONDITION);
	}
}
