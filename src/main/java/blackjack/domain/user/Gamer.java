package blackjack.domain.user;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.DeckStrategy;
import blackjack.domain.state.InitialTurn;
import blackjack.domain.state.State;

public abstract class Gamer {
	protected final Name name;

	protected State state;

	public Gamer(final String name, final DeckStrategy deck) {
		this.name = new Name(name);
		addTwoCards(deck);
	}

	public void addCard(final Card card){
		changeState(this.state.draw(card));
	}

	public void addTwoCards(final DeckStrategy deck) {
		Cards cards = new Cards();
		cards.addCard(deck.distributeCard());
		cards.addCard(deck.distributeCard());
		state = InitialTurn.createState(cards);
	}

	public boolean isBlackJack() {
		return this.state.getCards().isBlackJack();
	}

	public void changeState(State changedState) {
		this.state = changedState;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(this.state.getCards().getCards());
	}

	public int getScore() {
		return this.state.getCards().getScore();
	}

	public String getName() {
		return this.name.getName();
	}

	public boolean isRunning() {
		return this.state.isRunning();
	}

	public void stay() {
		changeState(state.stay());
	}

	public State state() {
		return this.state;
	}
}
