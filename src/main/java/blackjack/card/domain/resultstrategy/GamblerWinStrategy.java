package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public class GamblerWinStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		if ((gamblerCardBundle.isNotBurst() && dealerCardBundle.isBurst())
			|| isGreatorThan(gamblerCardBundle, dealerCardBundle)) {
			return true;
		}
		return false;
	}
}
