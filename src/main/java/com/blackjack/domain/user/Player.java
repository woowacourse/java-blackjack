package com.blackjack.domain.user;

public class Player extends User {
	private static final int DRAW_CONDITION = 20;

	Player(Name name) {
		super(name);
	}

	@Override
	public boolean canDraw() {
		int score = hand.calculateScore();
		return score <= DRAW_CONDITION;
	}
}
