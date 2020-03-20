package com.blackjack.domain.user;

import com.blackjack.domain.Score;

public class Dealer extends User {
	public static final int DRAW_CONDITION = 17;
	private static final Name DEALER_NAME = new Name("딜러");

	public Dealer() {
		super(DEALER_NAME);
	}

	@Override
	public boolean canDraw() {
		Score score = hand.calculate();
		return score.isLowerThan(DRAW_CONDITION);
	}
}
