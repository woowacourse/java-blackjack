package domain;

import java.util.List;

public class Cards {
	private List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = cards;
	}

	public int calculateTotalScore() {
		return cards.stream()
			.mapToInt(Card::score)
			.sum();
	}

	public boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}
}
