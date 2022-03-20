package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Blackjack extends Finished {

	private static final double BLACKJACK_VICTORY_RATE = 1.5;

	Blackjack(final Cards cards) {
		super(cards);
	}

	@Override
	public double earningRate(final Cards cards) {
		if (cards.isBlackjack()) {
			return TIE_RATE;
		}
		return BLACKJACK_VICTORY_RATE;
	}
}
