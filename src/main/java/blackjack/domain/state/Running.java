package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends Started {

	Running(Cards cards) {
		super(cards);
	}

	@Override
	public State stay() {
		return new Stay(cards);
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
}
