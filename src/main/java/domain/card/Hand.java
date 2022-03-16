package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private static final int LIMIT_TO_NOT_BUST_SCORE = 21;
	private static final int BLACKJACK_SCORE = 21;
	private static final int ADDITIONAL_SCORE_ACE = 10;
	public static final int BLACKJACK_SIZE = 2;
	private static final int ACE_COUNT_LOWER_BOUND = 0;

	private final List<Card> hand;

	public Hand(List<Card> hand) {
		this.hand = hand;
	}

	public static Hand copyOf(Hand hand) {
		return new Hand(new ArrayList<>(hand.hand));
	}

	public boolean isBlackJack() {
		return hand.size() == BLACKJACK_SIZE && getScore() == BLACKJACK_SCORE;
	}

	public boolean isBust() {
		return sumPoint() > LIMIT_TO_NOT_BUST_SCORE;
	}

	public boolean isMaxScore() {
		return getScore() == LIMIT_TO_NOT_BUST_SCORE;
	}

	public int getScore() {
		int aceCount = (int)hand.stream()
			.filter(Card::isAce)
			.count();
		int score = sumPoint();

		while (aceCount > ACE_COUNT_LOWER_BOUND && score + ADDITIONAL_SCORE_ACE <= LIMIT_TO_NOT_BUST_SCORE) {
			score += ADDITIONAL_SCORE_ACE;
			aceCount--;
		}
		return score;
	}

	public void add(Card card) {
		hand.add(card);
	}

	public List<Card> getCards() {
		return hand;
	}

	private int sumPoint() {
		return hand.stream()
			.mapToInt(Card::getPoint)
			.sum();
	}
}
