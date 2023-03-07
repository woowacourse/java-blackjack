package domain;

import java.util.List;

public abstract class User {

	protected Cards cards;
	protected String name;

	public User() {
		cards = new Cards();
	}

	public void hit(Card card) {
		cards.addCard(card);
	}

	public abstract boolean isHittable();

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	public int getScore() {
		return cards.calculateScore();
	}

	public List<String> getCardNames() {
		return cards.getCardNames();
	}
}
