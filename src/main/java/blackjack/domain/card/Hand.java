package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackJack;

public class Hand {

	private static final int ACE_AS_ELEVEN = 10;
	private static final int THE_NUMBER_OF_INITIAL_CARD = 2;

	private final List<Card> cards;

	public Hand() {
		this(new ArrayList<>());
	}

	public Hand(final List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(final Card card) {
		cards.add(card);
	}

	public String getFinalScore() {
		int score = calculateOptimalScore();
		if (score == BlackJack.BUST) {
			return BlackJack.BUST_MESSAGE;
		}
		return Integer.toString(score);
	}

	public int calculateOptimalScore() {
		int totalScore = cards.stream()
			.mapToInt(Card::getScore)
			.sum();
		if (isBust(totalScore)) {
			return BlackJack.BUST;
		}
		if (hasAce()) {
			return getOptimizedScore(totalScore, totalScore + ACE_AS_ELEVEN);
		}
		return totalScore;
	}

	private boolean isBust(final int totalScore) {
		return totalScore > BlackJack.OPTIMIZED_WINNING_NUMBER;
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

	public List<String> getCardsName() {
		return cards.stream()
			.map(Card::getName)
			.collect(Collectors.toList());
	}

	public boolean isBlackJack() {
		return calculateOptimalScore() == BlackJack.OPTIMIZED_WINNING_NUMBER
			&& cards.size() == THE_NUMBER_OF_INITIAL_CARD;
	}
}
