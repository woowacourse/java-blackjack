package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public class GamblerLoseStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return gamblerCardBundle.isBurst()
			|| isGreatorThan(dealerCardBundle, gamblerCardBundle)
			|| isBlackjackWin(dealerCardBundle, gamblerCardBundle);
	}
}
