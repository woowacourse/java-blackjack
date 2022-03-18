package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public class Hit implements State {
	private final Cards cards;

	public Hit(Cards cards) {
		this.cards = cards;
	}

	@Override
	public State draw(Card card) {
		this.cards.addCard(card);

		if (this.cards.isBust()) {
			return new Bust(cards);
		}
		return new Hit(cards);
	}

	@Override
	public State stay() {
		return new Stay(this.cards);
	}

	@Override
	public Money calculateProfit(Money money, Dealer dealer) {
		throw new IllegalStateException();
	}

	@Override
	public boolean isRunning() {
		return true;
	}

	@Override
	public Cards getCards() {
		return this.cards;
	}
}
