package blackjack.player;

import blackjack.player.card.Card;
import blackjack.player.card.CardBundle;

public abstract class Player {
	protected final CardBundle cardBundle;

	public Player(CardBundle cardBundle) {
		this.cardBundle = cardBundle;
	}

	public void addCard(Card card) {
		cardBundle.addCard(card);
	}
}
