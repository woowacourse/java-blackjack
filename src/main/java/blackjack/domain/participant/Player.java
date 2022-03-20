package blackjack.domain.participant;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.Outcome;
import blackjack.domain.card.Hand;

public class Player extends Participant {

	public Player(final String name, final Hand hand, final BettingAmount bettingAmount) {
		super(name, hand, bettingAmount);
	}

	@Override
	public void getBattingAmountFrom(final Participant dealer) {
		final Outcome outcome = judgeCompeteResult(calculateFinalScore(), dealer.calculateFinalScore());
		if (winWithoutBlackJack(outcome)) {
			battingAmount.giveTwoTimes();
		}
	}

	private boolean winWithoutBlackJack(final Outcome outcome) {
		return outcome == Outcome.VICTORY && !hand.isBlackJack();
	}

	@Override
	public boolean canDraw() {
		final int score = hand.calculateScore();
		if (hand.isBlackJack()) {
			return false;
		}
		return BlackJack.BUST < score && score <= BlackJack.OPTIMIZED_WINNING_NUMBER;
	}

	@Override
	public int getDrawStandard() {
		return BlackJack.OPTIMIZED_WINNING_NUMBER - 1;
	}
}
