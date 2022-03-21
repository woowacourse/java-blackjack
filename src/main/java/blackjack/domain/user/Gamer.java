package blackjack.domain.user;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.state.StateFactory;
import blackjack.domain.state.State;

public abstract class Gamer {
	protected final Name name;

	protected State state;

	protected Gamer(final String name, final Deck deck) {
		this.name = new Name(name);
		state = initialState(deck);
	}

	protected Gamer(final Name name) {
		this.name = name;
	}

	private State initialState(final Deck deck) {
		Cards cards = addTwoCards(deck);
		return StateFactory.createState(cards);
	}

	private Cards addTwoCards(Deck deck) {
		Cards cards = new Cards();
		cards.addCard(deck.distributeCard());
		cards.addCard(deck.distributeCard());
		return cards;
	}

	public boolean isBlackJack() {
		return this.state.getCards().isBlackJack();
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

	public State state() {
		return this.state;
	}

	public Cards cards() {
		return this.state.getCards();
	}

	public void addCard(final Card card){
		changeState(this.state.draw(card));
	}

	public void stay() {
		changeState(state.stay());
	}

	private void changeState(State changedState){
		this.state = changedState;
	}
}
