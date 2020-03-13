package com.blackjack.domain.user;

import com.blackjack.domain.Score;

public class Dealer extends User {
	public static final int DRAW_CONDITION = 16;
	private static final Name DEALER_NAME = new Name("딜러");

	public Dealer() {
		super(DEALER_NAME);
	}

	@Override
	public boolean canDraw() {
		Score score = hand.calculateScore();
		return score.isLowerThan(DRAW_CONDITION);
	}
}
