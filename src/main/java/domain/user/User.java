package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;

public abstract class User {
	private static final int FIRST_CARD_DRAW_SIZE = 2;

	protected final Name name;
	protected final Cards cards;

	public User(Name name) {
		this(name, new Cards(new ArrayList<>()));
	}

	public User(Name name, Cards cards) {
		this.name = Objects.requireNonNull(name);
		this.cards = Objects.requireNonNull(cards);
	}

	public void drawFirst(CardDeck deck) {
		for (int i = 0; i < FIRST_CARD_DRAW_SIZE; i++) {
			draw(deck);
		}
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

	public boolean hasHigherScoreThan(User other) {
		return this.calculateScore() > other.calculateScore();
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	public String getName() {
		return name.getName();
	}

	public List<Card> getFirstShowCards() {
		return cards.getSubList(getFirstShowCardSize());
	}

	protected abstract int getFirstShowCardSize();

	public abstract boolean isDrawable();
}
