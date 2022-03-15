package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	public static final int BUST = 0;
	public static final int OPTIMIZED_WINNING_NUMBER = 21;

	private static final int MATCH_BLACKJACK_SIZE = 2;
	private static final int ACE_AS_ELEVEN = 10;

	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void addCard(final Card card) {
		cards.add(card);
	}

	public boolean isBlackJack() {
		return calculateOptimalScore() == OPTIMIZED_WINNING_NUMBER && cards.size() == MATCH_BLACKJACK_SIZE;
	}

	public int calculateOptimalScore() {
		int totalScore = cards.stream()
				.mapToInt(Card::getScore)
				.sum();
		if (isBust(totalScore)) {
			return BUST;
		}
		if (hasAce()) {
			return getOptimizedScore(totalScore, totalScore + ACE_AS_ELEVEN);
		}
		return totalScore;
	}

	private boolean isBust(int totalScore) {
		return totalScore > OPTIMIZED_WINNING_NUMBER;
	}

	public boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	private int getOptimizedScore(final int aceAsOneScore, final int aceAsElevenScore) {
		if (isBust(aceAsElevenScore)) {
			return aceAsOneScore;
		}
		return aceAsElevenScore;
	}

	public boolean isBustScore(int totalScore) {
		return totalScore == BUST || totalScore > OPTIMIZED_WINNING_NUMBER;
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}
}
