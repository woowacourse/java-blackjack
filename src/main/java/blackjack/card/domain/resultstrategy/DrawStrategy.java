package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public class DrawStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		if (dealerCardBundle.isBlackjack() && gamblerCardBundle.isBlackjack()) {
			return true;
		}
		if (dealerCardBundle.isNotBurst() && gamblerCardBundle.isNotBurst()
			&& dealerCardBundle.isSameScore(gamblerCardBundle)
			&& checkNotBlackjack(dealerCardBundle, gamblerCardBundle)) {
			return true;
		}
		return false;
	}

	private boolean checkNotBlackjack(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return !dealerCardBundle.isBlackjack() && !gamblerCardBundle.isBlackjack();
	}
}
