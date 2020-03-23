package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public class GamblerBlackJackWinStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return isBlackjackWin(gamblerCardBundle, dealerCardBundle);
	}
}
