package blackjack.domain;

import java.util.List;

public class Gamer {
	private static final int BUST_THRESHOLD = 21;
	protected final Cards cards = new Cards();
	protected final Name name;

	public Gamer(Name name) {
		this.name = name;
	}

	public void addCards(int times) {
		for (int i = 0; i < times; i++) {
			processCard(Deck.distributeCard());
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

	public boolean isBust() {
		return this.cards.getScore() > BUST_THRESHOLD;
	}

	public String getName() {
		return this.name.getName();
	}

	public boolean isBlackJack() {
		return this.cards.getCards().size() == 2 && this.getScore() == 21;
	}
}
