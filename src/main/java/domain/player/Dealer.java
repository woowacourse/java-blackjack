package domain.player;

import domain.card.PlayerCards;

public class Dealer extends Player {
	public Dealer() {
		this.name = "딜러";
		this.playerCards = new PlayerCards();
	}

	public String toStringOneCard() {
		return name + " : " + playerCards.toStringOneCard();
	}
}
