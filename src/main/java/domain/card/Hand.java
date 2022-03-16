package domain.card;

import java.util.List;
import java.util.stream.Collectors;

public class Hand {
	private static final int STANDARD_BUST_SCORE = 21;
	private static final int ACE_COUNT_LOWER_BOUND = 0;
	private static final int ADDITIONAL_SCORE_ACE = 10;
	private static final int FIRST_CARD_INDEX = 0;
	private final List<Card> hand;

	public Hand(List<Card> hand) {
		this.hand = hand;
	}

	public boolean isBust() {
		return getMinScore() > STANDARD_BUST_SCORE;
	}

	private int getMinScore() {
		return hand.stream().mapToInt(Card::getPoint).sum();
	}

	public boolean isMaxScore() {
		return getBestScore() == STANDARD_BUST_SCORE;
	}

	public int getBestScore() {
		int aceCount = (int)hand.stream()
			.filter(Card::isAce)
			.count();
		int bestScore = getMinScore();

		while (aceCount > ACE_COUNT_LOWER_BOUND && bestScore + ADDITIONAL_SCORE_ACE <= STANDARD_BUST_SCORE) {
			bestScore += ADDITIONAL_SCORE_ACE;
			aceCount--;
		}
		return bestScore;
	}

	public void add(Card card) {
		hand.add(card);
	}

	public Card getOneHand() {
		return hand.get(FIRST_CARD_INDEX);
	}

	public List<CardDTO> getInfo() {
		return hand.stream()
			.map(Card::getInfo)
			.collect(Collectors.toList());
	}
}
