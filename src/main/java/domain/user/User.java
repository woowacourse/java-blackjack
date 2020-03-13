package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import domain.card.Card;
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
