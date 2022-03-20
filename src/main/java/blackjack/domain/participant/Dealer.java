package blackjack.domain.participant;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.Outcome;
import blackjack.domain.card.Hand;

public class Dealer extends Participant {

	private static final int CAN_DRAW_STANDARD = 16;
	private static final String DEALER_NAME = "딜러";

	public Dealer(final Hand hand) {
		super(DEALER_NAME, hand, new BettingAmount(10));
	}

	@Override
	public void getBattingAmountFrom(final Participant player) {
		final Outcome outcome = judgeCompeteResult(calculateFinalScore(), player.calculateFinalScore());
		if (outcome == Outcome.VICTORY) {
			final int finalIncome = battingAmount.getTotalValue() + player.getCurrentIncome();
			battingAmount = new BettingAmount(finalIncome, battingAmount.getInitialValue());
			player.loseAll();
		}
	}

	@Override
	public boolean canDraw() {
		final int score = hand.calculateScore();
		return BlackJack.BUST_SCORE < score && score <= CAN_DRAW_STANDARD;
	}

	@Override
	public int getDrawStandard() {
		return CAN_DRAW_STANDARD;
	}
}
