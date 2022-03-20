package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Bust extends Finished {

	Bust(final Cards cards) {
		super(cards);
	}

	@Override
	public double earningRate(final Cards cards) {
		return DEFEAT_RATE;
	}
}
