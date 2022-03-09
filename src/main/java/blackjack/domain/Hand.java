package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void addCard(final Card card) {
		cards.add(card);
	}

	public int calculateOptimalScore() {
		int totalScore = cards.stream()
			.mapToInt(Card::getScore)
			.sum();
		if (isBust(totalScore)) {
			return 0;
		}
		if (hasAce()) {
			return getOptimizedScore(totalScore, totalScore + 10);
		}
		return totalScore;
	}

	private boolean isBust(int totalScore) {
		return totalScore > 21;
	}

	public boolean hasAce() {
		return cards.stream()
			.mapToInt(Card::getScore)
			.anyMatch(score -> score == Denomination.ACE.getScore());
	}

	private int getOptimizedScore(final int aceAsOneScore, final int aceAsElevenScore) {
		if (isBust(aceAsElevenScore)) {
			return aceAsOneScore;
		}
		return aceAsElevenScore;
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}
}
