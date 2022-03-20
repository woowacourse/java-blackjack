package domain.participant.info;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class Hand {
	private static final int WINNING_SCORE = 21;
	private static final int ADDITIONAL_SCORE_ACE = 10;
	private static final int BLACKJACK_SIZE = 2;

	private final List<Card> hand;

	public Hand(List<Card> hand) {
		this.hand = hand;
	}

	public boolean isBlackJack() {
		return hand.size() == BLACKJACK_SIZE && getScore() == WINNING_SCORE;
	}

	public boolean isBust() {
		return sumPoint() > WINNING_SCORE;
	}

	public int getScore() {
		int score = sumPoint();

		if (hasAce() && canAddAcePoint(score)) {
			score += ADDITIONAL_SCORE_ACE;
		}

		return score;
	}

	private boolean hasAce() {
		return hand.stream().anyMatch(Card::isAce);
	}

	private boolean canAddAcePoint(int score) {
		return score + ADDITIONAL_SCORE_ACE <= WINNING_SCORE;
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
