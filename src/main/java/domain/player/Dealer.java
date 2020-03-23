package domain.player;

import domain.Rule;

public class Dealer extends Player {
	private static final String DEALER_NAME = "딜러";

	public Dealer() {
		super(DEALER_NAME);
	}

	public boolean isHit() {
		return playerCards.calculateScore() <= Rule.DEALER_HIT_SCORE;
	}
}
