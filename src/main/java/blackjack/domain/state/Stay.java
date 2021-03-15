package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;
import blackjack.domain.participant.Money;

public class Stay extends Finished {
	public Stay(ParticipantCards participantCards) {
		super(participantCards);
	}

	@Override
	public boolean isBust() {
		return false;
	}

	@Override
	public boolean isBlackJack() {
		return false;
	}

	@Override
	public double makeProfit(PlayerState comparingState, Money money) {
		if (comparingState.calculatePoint() == calculatePoint()) {
			return money.multiplyMoneyWithOperation(DRAW_PROFIT_RATE);
		}
		if (!comparingState.isBust() && comparingState.calculatePoint() > calculatePoint()) {
			return money.multiplyMoneyWithOperation(LOSE_PROFIT_RATE);
		}
		return money.multiplyMoneyWithOperation(WIN_PROFIT_RATE);
	}
}
