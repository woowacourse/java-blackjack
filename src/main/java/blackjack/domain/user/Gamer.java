package blackjack.domain.user;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class Gamer {
	protected final Cards cards;
	protected final Name name;

	public Gamer(final String name) {
		this.cards = new Cards();
		this.name = new Name(name);
	}

	public void addCard(final Card card) {
		this.cards.addCard(card);
	}

	public void addTwoCards(final Deck deck) {
		addCard(deck.distributeCard());
		addCard(deck.distributeCard());
	}

	public boolean isBlackJack() {
		return this.cards.isBlackJack();
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(this.cards.getCards());
	}

	public int getScore() {
		return this.cards.getScore();
	}

	public boolean isBust() {
		return this.cards.isBust();
	}

	public String getName() {
		return this.name.getName();
	}
}
