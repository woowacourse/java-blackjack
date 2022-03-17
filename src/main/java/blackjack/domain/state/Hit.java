package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements State {
	private final Cards cards;

	public Hit(Cards cards) {
		this.cards = cards;
	}

	@Override
	public State draw(Card card) {
		this.cards.addCard(card);

		if (this.cards.isBust()) {
			return new Bust(cards);
		}
		return new Hit(cards);
	}
}
