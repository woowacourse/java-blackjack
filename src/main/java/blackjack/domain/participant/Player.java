package blackjack.domain.participant;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.Outcome;
import blackjack.domain.card.Hand;

public class Player extends Participant {

	private boolean drawMore;

	public Player(final String name, final Hand hand, final BettingAmount bettingAmount) {
		super(name, hand, bettingAmount);
		this.drawMore = true;
	}

	@Override
	public void getBettingAmountFrom(final Participant dealer) {
		final Outcome outcome = judgeCompeteResult(calculateFinalScore(), dealer.calculateFinalScore());
		if (winWithoutBlackJack(outcome)) {
			bettingAmount.giveOneTime();
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
	public boolean selectToDrawMore() {
		return drawMore;
	}

	@Override
	public void stopDraw() {
		drawMore = false;
	}

	@Override
	public int getDrawStandard() {
		return BlackJack.OPTIMIZED_WINNING_NUMBER - 1;
	}
}
