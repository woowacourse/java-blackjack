package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;
import blackjack.domain.participant.Money;

public class BlackJack extends Finished {
	private static final double BLACKJACK_PROFIT_RATE = 1.5;

	public BlackJack(ParticipantCards participantCards) {
		super(participantCards);
	}

	@Override
	public boolean isBust() {
		return false;
	}

	@Override
	public boolean isBlackJack() {
		return true;
	}

	@Override
	public double makeProfit(PlayerState comparingState, Money money) {
		if (comparingState.isBlackJack()) {
			return money.multiplyMoneyWithOperation(WIN_PROFIT_RATE);
		}
		return money.multiplyMoneyWithOperation(BLACKJACK_PROFIT_RATE);
	}
}
