package domain;

import java.util.Collections;
import java.util.List;

public class CardHand {
	private final List<Card> cards;

	public CardHand(List<Card> cards) {
		this.cards = cards;
	}

	public void add(Card card) {
		cards.add(card);
	}

	public void add(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public int calculateScore() {
		int totalScore = cards.stream()
			.map(Card::getScore)
			.reduce(0, Integer::sum);

		return calculateA(totalScore);
	}

	private int calculateA(final int totalScore) {
		boolean hasA = cards.stream()
			.anyMatch(card -> card.isRank(Rank.A));

		if (hasA && totalScore <= 11) {
			return totalScore + 10;
		}
		return totalScore;
	}

	public boolean isBurst() {
		return calculateScore() > 21;
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
