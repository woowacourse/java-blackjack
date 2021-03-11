package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running implements PlayerState {
	protected Cards cards;

	public Running(Cards cards) {
		this.cards = cards;
	}

	@Override
	public int calculatePoint() {
		return cards.calculateJudgingPoint();
	}
}
