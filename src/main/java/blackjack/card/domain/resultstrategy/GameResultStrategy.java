package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public abstract class GameResultStrategy {
	public abstract boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle);

	protected boolean isGreatorThan(CardBundle greator, CardBundle less) {
		return (greator.isNotBurst() && less.isNotBurst())
			&& (greator.calculateScore() > less.calculateScore());
	}

	protected boolean isBlackjackWin(CardBundle winner, CardBundle loser) {
		return winner.isBlackjack()
			&& !loser.isBlackjack();
	}
}
