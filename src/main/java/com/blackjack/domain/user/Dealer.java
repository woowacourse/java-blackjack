package com.blackjack.domain.user;

public class Dealer extends User {
	public static final int DRAW_CONDITION = 16;
	private static final Name DEALER_NAME = new Name("딜러");

	public Dealer() {
		super(DEALER_NAME);
	}

	@Override
	public boolean canDraw() {
		int score = hand.calculateScore();
		return score <= DRAW_CONDITION;
	}
}
