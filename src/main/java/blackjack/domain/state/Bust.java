package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public class Bust implements State{
	private final Cards cards;

	public Bust(Cards cards) {
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
		return money.multiply(profitRate());
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public Cards getCards() {
		return this.cards;
	}

	public double profitRate() {
		return -1;
	}
}
