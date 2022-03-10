package blackjack.domain;

import java.util.List;

public class Gamer {
	private final Cards cards = new Cards();
	protected int score = 0;

	public void addCards(Deck deck, int times) {
		for (int i = 0; i < times; i++) {
			processCard(deck.distributeCard());
		}
	}

	private void processCard(Card card) {
		this.score = cards.addCard(card);
	}

	public List<Card> getCards() {
		return this.cards.getCards();
	}

	public int getScore() {
		return score;
	}

	public boolean isBurst() {
		return this.score < 0;
	}
}
