package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public final class Stay extends Finished {

	protected Stay(Cards cards) {
		super(cards);
	}

	protected double profitRate(Dealer dealer) {
		if (dealer.compare(this.cards) > 0) {
			return -1;
		}
		if (dealer.compare(this.cards) < 0) {
			return 1;
		}
		return 0;
	}
}
