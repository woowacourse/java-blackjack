package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;
import blackjack.domain.participant.Money;

public class Bust extends Finished {
	public Bust(ParticipantCards participantCards) {
		super(participantCards);
	}

	@Override
	public boolean isBust() {
		return true;
	}

	@Override
	public boolean isBlackJack() {
		return false;
	}

	@Override
	public double makeProfit(PlayerState comparingState, Money money) {
		return money.multiplyMoneyWithOperation(LOSE_PROFIT_RATE);
	}
}
