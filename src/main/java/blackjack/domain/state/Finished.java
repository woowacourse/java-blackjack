package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished implements PlayerState {
	protected Cards cards;

	public Finished(Cards cards) {
		this.cards = cards;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public PlayerState keepContinue(boolean input) {
		return this;
	}

	@Override
	public PlayerState drawNewCard(Card card) {
		throw new IllegalArgumentException("옳지 않은 곳에서 호출");
	}

	@Override
	public Cards cards() {
		return cards;
	}

	@Override
	public int calculatePoint() {
		return cards.calculateIncludeAce();
	}
}
