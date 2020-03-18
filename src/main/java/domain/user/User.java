package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;

public abstract class User {
	protected final Name name;
	protected final Cards cards;

	public User(Name name) {
		this(name, new Cards(new ArrayList<>()));
	}

	public User(Name name, Cards cards) {
		this.name = Objects.requireNonNull(name);
		this.cards = Objects.requireNonNull(cards);
	}

	public void draw(CardDeck deck) {
		cards.add(deck.draw());
	}

	public boolean isBlackjack() {
		return cards.isBlackjack();
	}

	public boolean isBust() {
		return this.cards.isBust();
	}

	public int calculateScore() {
		return cards.calculateScore();
	}

	public boolean hasLowerScoreThan(User other) {
		return this.calculateScore() < other.calculateScore();
	}

	public boolean hasHigherScoreThan(User other) {
		return this.calculateScore() > other.calculateScore();
	}

	public boolean hasSameScoreWith(User other) {
		return this.calculateScore() == other.calculateScore();
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	public String getName() {
		return name.getName();
	}

	public List<Card> getFirstOpenCards() {
		return cards.getSubList(getFirstShowCardSize());
	}

	public abstract int getFirstShowCardSize();

	public abstract boolean isDrawable();
}
