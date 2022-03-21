package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public class Dealer extends Gamer {
	private static final String DEALER_NAME = "딜러";

	public Dealer(final Deck deck) {
		super(DEALER_NAME, deck);
	}

	public int compare(final Cards otherCards) {
		return this.state.getCards().compare(otherCards);
	}

	public boolean isHit() {
		return this.state.getCards().isHit();
	}
}
