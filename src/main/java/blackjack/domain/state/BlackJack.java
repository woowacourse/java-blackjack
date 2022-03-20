package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public final class BlackJack extends Finished{

	protected BlackJack(Cards cards) {
		super(cards);
	}

	@Override
	protected double profitRate(Dealer dealer) {
		if (dealer.isBlackJack()) {
			return 0;
		}
		return 1.5;
	}
}
