package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished implements PlayerState {
	public static final int WIN_PROFIT_RATE = 1;
	public static final int DRAW_PROFIT_RATE = 0;
	public static final int LOSE_PROFIT_RATE = -1;

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
		throw new IllegalArgumentException(ERROR_MESSAGE_CALL);
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
