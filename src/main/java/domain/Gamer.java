package domain;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
	private final List<Card> cards = new ArrayList<>();
	protected int score = 0;

	// public void addCard(Deck deck) {
	// 	cards.add(deck.distributeCard());
	// }

	public void addCard(Card card) {
		cards.add(card);
		this.score += card.getScore();
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public boolean isBurst() {
		return this.score > 21;
	}
}
