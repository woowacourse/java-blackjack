package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Profit;

public abstract class Running implements PlayerState {
	protected Cards cards;

	public Running(Cards cards) {
		this.cards = cards;
	}

	@Override
	public int calculatePoint() {
		return cards.calculateJudgingPoint();
	}

	@Override
	public double makeProfit(Dealer dealer, Profit profit) {
		throw new IllegalArgumentException(ERROR_MESSAGE_CALL);
	}
}
