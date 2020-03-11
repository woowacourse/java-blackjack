package domain.player;

import domain.card.PlayerCards;

public class Dealer extends Player {
	private static final String DEALER_NAME = "딜러";

	public Dealer() {
		this.name = DEALER_NAME;
		this.playerCards = new PlayerCards();
	}
}
