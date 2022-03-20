package blackjack.domain.role;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.state.State;
import java.util.List;

public abstract class Role {

	protected final String name;
	protected State state;

	public Role(final String name, final State state) {
		this.name = name;
		this.state = state;
	}

	public final void ready(final Deck deck) {
		state = state.draw(deck.draw())
				.draw(deck.draw());
	}

	public final void stay() {
		state = state.stay();
	}

	public final boolean isFinished() {
		return state.isFinished();
	}

	public final int getScore() {
		return state.getScore();
	}

	public final Cards getCards() {
		return state.getCards();
	}

	public final String getName() {
		return name;
	}

	public abstract void draw(final Deck deck);

	public abstract List<Card> openCards();

	public abstract Money settle(Role dealer);
}
