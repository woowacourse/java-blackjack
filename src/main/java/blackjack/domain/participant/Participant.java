package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public abstract class Participant {

	protected final Name name;
	protected State state = new Ready();

	protected Participant(Name name) {
		this.name = name;
	}

	public abstract boolean isFinished();

	public boolean isReady() {
		return getCards().isReady();
	}

	public void draw(Card card) {
		state = state.draw(card);
	}

	public int score() {
		return getCards().sum();
	}

	public String getName() {
		return name.getValue();
	}

	public Cards getCards() {
		return state.getCards();
	}
}
