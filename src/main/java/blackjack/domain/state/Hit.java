package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {

	Hit(Cards cards) {
		super(cards);
	}

	@Override
	public final State draw(final Card card) {
		final Cards newCards = cards.add(card);
		if (newCards.isBust()) {
			return new Bust(newCards);
		}
		return new Hit(newCards);
	}

	@Override
	public final Stay stay() {
		return new Stay(cards);
	}
}
