package domain.participant.info;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class Hand {
	private static final int LIMIT_TO_NOT_BUST_SCORE = 21;
	private static final int BLACKJACK_SCORE = 21;
	private static final int ADDITIONAL_SCORE_ACE = 10;
	private static final int BLACKJACK_SIZE = 2;

	private final List<Card> hand;

	public Hand(List<Card> hand) {
		this.hand = hand;
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
		boolean hasAce = hand.stream().filter(Card::isAce).count() > 0;
		int score = sumPoint();

		if (hasAce && canAddAcePoint(score)) {
			score += ADDITIONAL_SCORE_ACE;
		}

		return score;
	}

	private boolean canAddAcePoint(int score) {
		if (score + ADDITIONAL_SCORE_ACE <= LIMIT_TO_NOT_BUST_SCORE) {
			return true;
		}

		return false;
	}

	public void add(Card card) {
		hand.add(card);
	}

	private int sumPoint() {
		return hand.stream()
			.mapToInt(Card::getPoint)
			.sum();
	}

	public List<String> show() {
		return hand.stream()
			.map(Card::getInfo)
			.collect(Collectors.toList());
	}
}
