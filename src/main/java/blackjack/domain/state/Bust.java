package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Bust implements State{
	private final Cards cards;

	public Bust(Cards cards) {
		this.cards = cards;
	}

	@Override
	public State draw(Card card) {
		throw new IllegalStateException();
	}

	@Override
	public State stay() {
		throw new IllegalStateException();
	}
}
