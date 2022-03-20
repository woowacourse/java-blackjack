package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class Ready extends Running {

	public Ready(Cards cards) {
		super(cards);
	}

	public Ready() {
		this(new Cards());
	}

	public State draw(Card card) {
		Cards drawingCards = cards.add(card);

		if (drawingCards.isNotStarted()) {
			return new Ready(drawingCards);
		}
		if (drawingCards.isBlackJack()) {
			return new BlackJack(drawingCards);
		}
		return new Hit(drawingCards);
	}
}
