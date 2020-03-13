package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;

public abstract class User {
	protected final Cards cards;
	protected final Name name;

	public User(Name name) {
		this(new Cards(new ArrayList<>()), name);
	}

	public User(Cards cards, Name name) {
		this.cards = Objects.requireNonNull(cards);
		this.name = Objects.requireNonNull(name);
	}

	public void addCards(Card... cards) {
		this.cards.addAll(Arrays.asList(cards));
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

	public List<Card> getCards() {
		return cards.getCards();
	}

	public String getName() {
		return name.getName();
	}

	public abstract List<Card> getInitialCard();
}
