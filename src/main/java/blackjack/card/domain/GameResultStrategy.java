package blackjack.card.domain;

public abstract class GameResultStrategy {
	public abstract boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle);

	protected boolean isGreatorThan(CardBundle greator, CardBundle less) {
		return greator.calculateScore() > less.calculateScore();
	}

	protected boolean isBlackjackWin(CardBundle winner, CardBundle loser) {
		return winner.isBlackjack()
			&& !loser.isBlackjack();
	}
}
