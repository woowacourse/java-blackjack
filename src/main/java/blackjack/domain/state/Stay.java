package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Stay extends Finished {

	private static final int ZERO = 0;

	Stay(final Cards cards) {
		super(cards);
	}

	@Override
	public double earningRate(final Cards cards) {
		if (cards.isBlackjack()) {
			return DEFEAT_RATE;
		}
		int compareResult = Integer.compare(this.cards.getScore(), cards.getScore());
		if (cards.isBust() || compareResult > ZERO) {
			return VICTORY_RATE;
		}
		if (compareResult == ZERO) {
			return TIE_RATE;
		}
		return DEFEAT_RATE;
	}
}
