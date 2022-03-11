package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.service.BlackJackService;

public class Hand {

	private static final int ACE_AS_ELEVEN = 10;

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
			return BlackJackService.BUST;
		}
		if (hasAce()) {
			return getOptimizedScore(totalScore, totalScore + ACE_AS_ELEVEN);
		}
		return totalScore;
	}

	private boolean isBust(int totalScore) {
		return totalScore > BlackJackService.OPTIMIZED_WINNING_NUMBER;
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
