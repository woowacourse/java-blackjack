package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class Hit extends Running {

	Hit(Cards cards) {
		super(cards);
	}

	@Override
	public State draw(Card card) {
		Cards drawingCards = cards.add(card);

		if (drawingCards.isBust()) {
			return new Bust(drawingCards);
		}
		return new Hit(drawingCards);
	}
}
