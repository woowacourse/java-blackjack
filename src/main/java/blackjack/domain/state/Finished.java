package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;

import java.util.stream.Stream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished implements PlayerState {
	protected static final int WIN_PROFIT_RATE = 1;
	protected static final int DRAW_PROFIT_RATE = 0;
	protected static final int LOSE_PROFIT_RATE = -1;

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
	public int calculatePoint() {
		return cards.calculateIncludeAce();
	}

	@Override
	public Stream<Card> getCardStream() {
		return cards.getCardStream();
	}
}
