package blackjack.domain.user;

import blackjack.domain.card.Cards;

public class Dealer extends Gamer {
	private static final String DEALER_NAME = "딜러";

	public Dealer() {
		super(DEALER_NAME);
	}

	public int compare(final Gamer gamer) {
		return this.cards.compare(gamer.cards);
	}

	public int compare2(final Cards otherCards) {
		return this.cards.compare(otherCards);
	}

	public boolean isHit() {
		return this.cards.isHit();
	}
}
