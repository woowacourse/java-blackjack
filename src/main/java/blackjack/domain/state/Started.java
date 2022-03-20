package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;

public abstract class Started implements State {

	protected final Cards cards;

	public Started(final Cards cards) {
		this.cards = cards;
	}

	public abstract State draw(final Card card);

	public abstract Stay stay();

	public abstract boolean isFinished();

	public abstract Money profit(Cards cards, Money money);

	@Override
	public final Cards getCards() {
		return cards;
	}

	@Override
	public int getScore() {
		return cards.getScore();
	}
}
