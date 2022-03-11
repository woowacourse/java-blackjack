package blackjack.domain;

import java.util.List;

public class Gamer {
	private final Cards cards = new Cards();
	protected final Name name;

	public Gamer(Name name) {
		this.name = name;
	}

	public void addCards(Deck deck, int times) {
		for (int i = 0; i < times; i++) {
			processCard(deck.distributeCard());
		}
	}

	public void processCard(Card card) {
		this.cards.addCard(card);
	}

	public List<Card> getCards() {
		return this.cards.getCards();
	}

	public int getScore() {
		return this.cards.getScore();
	}

	public boolean isBurst() {
		return this.cards.getScore() < 0;
	}

	public Card getRandomOneCard() {
		return this.cards.getRandomCard();
	}

	public String getName() {
		return this.name.getName();
	}
}
