package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {
	public static final int THRESHOLD_OF_BUST = 21;

	public Hit(Cards cards) {
		super(cards);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public PlayerState keepContinue(boolean input) {
		if (!input) {
			return new Stay(cards);
		}
		return this;
	}

	@Override
	public PlayerState drawNewCard(Card card) {
		cards.addCard(card);
		if (cards.calculateIncludeAce() > THRESHOLD_OF_BUST) {
			return new Bust(cards);
		}
		return this;
	}

	@Override
	public Cards cards() {
		return cards;
	}

	@Override
	public boolean isBust() {
		return false;
	}

	@Override
	public boolean isBlackJack() {
		return false;
	}
}
