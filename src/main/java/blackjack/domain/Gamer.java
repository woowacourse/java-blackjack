package blackjack.domain;

import java.util.List;

public class Gamer {
	protected final Cards cards = new Cards();
	protected final Name name;

	public Gamer(Name name) {
		this.name = name;
	}

	public void addCards(Card card, int times) {
		for (int i = 0; i < times; i++) {
			addCard(card);
		}
	}

	public void addCard(Card card) {
		this.cards.addCard(card);
	}

	public List<Card> getCards() {
		return this.cards.getCards();
	}

	public int getScore() {
		return this.cards.getScore();
	}

	public String getName() {
		return this.name.getName();
	}

	public boolean isBust() {
		return this.cards.isBust();
	}

	public boolean isBlackJack() {
		return this.cards.isBlackJack();
	}
}
