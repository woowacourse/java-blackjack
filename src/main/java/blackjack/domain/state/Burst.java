package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Burst extends Finished {
	public Burst(Cards cards) {
		super(cards);
	}

	@Override
	public boolean isBurst() {
		return true;
	}
}
