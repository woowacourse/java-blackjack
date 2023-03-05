package domain;

import java.util.List;

public abstract class User {

	final Cards cards = new Cards();
	String name;

	public String getName() {
		return name;
	}

	public void hit(Card card) {
		cards.addCard(card);
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	abstract public boolean isHittable();

	public int getScore() {
		return cards.calculateScore();
	}
}
