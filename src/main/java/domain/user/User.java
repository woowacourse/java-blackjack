package domain.user;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.score.Score;

public abstract class User {
	private static final int FIRST_DRAW_COUNT = 2;

	protected final Name name;
	protected final Cards cards;

	public User(Name name, Cards cards) {
		this.name = Objects.requireNonNull(name);
		this.cards = Objects.requireNonNull(cards);
	}

	public void drawFirstTime(CardDeck deck) {
		for (int i = 0; i < FIRST_DRAW_COUNT; i++) {
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

	public Score calculateScore() {
		return cards.calculateScore();
	}

	public boolean hasLowerScoreThan(User other) {
		return calculateScore().isSmallerThan(other.calculateScore());
	}

	public boolean hasHigherScoreThan(User other) {
		return calculateScore().isBiggerThan(other.calculateScore());
	}

	public boolean hasSameScoreWith(User other) {
		return calculateScore().isSameWith(other.calculateScore());
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
