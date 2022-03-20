package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public final class Bust extends Finished{

	protected Bust(Cards cards) {
		super(cards);
	}

	@Override
	public double profitRate(Dealer dealer) {
		return -1;
	}
}
