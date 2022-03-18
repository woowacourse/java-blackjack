package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public class Stay implements State {
	private final Cards cards;

	public Stay(Cards cards) {
		this.cards = cards;
	}

	@Override
	public State draw(Card card) {
		throw new IllegalStateException();
	}

	@Override
	public State stay() {
		throw new IllegalStateException();
	}

	@Override
	public Money calculateProfit(Money money, Dealer dealer) {
		return money.multiply(profitRate(dealer));
	}

	public double profitRate(Dealer dealer) {
		if (dealer.compare2(this.cards) > 0) {
			return -1;
		}
		if (dealer.compare2(this.cards) < 0) {
			return 1;
		}
		return 0;
	}
}
