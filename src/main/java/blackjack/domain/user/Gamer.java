package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public class Gamer {
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

	public List<Card> getCards() {
		return this.cards.getCards();
	}

	public int getScore() {
		return this.cards.getScore();
	}

	public boolean isBurst() {
		return this.cards.isBust();
	}

	public String getName() {
		return this.name.getName();
	}

}
