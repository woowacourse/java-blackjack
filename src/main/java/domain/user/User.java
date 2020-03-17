package domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.BlackjackUtils;
import domain.card.Card;
import domain.card.Cards;

public abstract class User {
	protected final Cards cards;

	public User() {
		this.cards = new Cards(new ArrayList<>());
	}

	public void addCards(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public boolean isBlackjack() {
		return BlackjackUtils.isBlackjack(cards);
	}

	public boolean isBust() {
		return BlackjackUtils.isBust(cards);
	}

	public int calculateScore() {
		return cards.calculateScore();
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	public abstract List<Card> getInitialCard();

	public abstract String getName();
}
