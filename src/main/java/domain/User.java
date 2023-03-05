package domain;

import java.util.List;

public abstract class User {

	Cards cards;
	String name;

	public User(){
		cards = new Cards();
		hitTwoCards();
	}

	private void hitTwoCards() {
		hit(Deck.pickCard());
		hit(Deck.pickCard());
	}

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

	public List<String> getCardNames() {
		return cards.getCardNames();
	}
}
