package blackjack.domain.participant;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackJack;
import blackjack.domain.Outcome;
import blackjack.domain.card.Hand;

public class Dealer extends Participant {

	private static final int CAN_DRAW_STANDARD = 16;
	private static final String DEALER_NAME = "딜러";
	private static final String INVALID_METHOD_CALL = "유효하지 않은 메서드입니다";

	public Dealer(final Hand hand) {
		super(DEALER_NAME, hand, new BettingAmount(10));
	}

	@Override
	public void getBettingAmountFrom(final Participant player) {
		final Outcome outcome = judgeCompeteResult(calculateFinalScore(), player.calculateFinalScore());
		if (outcome == Outcome.VICTORY) {
			final int finalIncome = bettingAmount.getTotalValue() + player.getCurrentIncome();
			bettingAmount = new BettingAmount(finalIncome, bettingAmount.getInitialValue());
			player.loseAll();
		}
	}

	@Override
	public boolean canDraw() {
		final int score = hand.calculateScore();
		return BlackJack.BUST_SCORE < score && score <= CAN_DRAW_STANDARD;
	}

	@Override
	public void stopDraw() {
		throw new IllegalArgumentException(INVALID_METHOD_CALL);
	}

	@Override
	public boolean selectToDrawMore() {
		throw new IllegalArgumentException(INVALID_METHOD_CALL);
	}

	@Override
	public int getDrawStandard() {
		return CAN_DRAW_STANDARD;
	}
}
