package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Hand {
	private static final int MAX_SCORE = 21;
	private static final int MAX_SCORE_TO_MAXIMIZE = 12;
	private static final int ADDING_SCORE_TO_MAXIMIZE = 10;
	private static final int TWO = 2;

	private final List<Card> hand;

	public Hand() {
		hand = new ArrayList<>();
	}

	public Hand(List<Card> cards) {
		hand = cards;
	}

	public void add(Card card) {
		hand.add(card);
	}

	public void addAll(List<Card> cards) {
		hand.addAll(cards);
	}

	public Score computeScore() {
		Score simpleSum = simplySumScores();
		return maximizeSimpleSumIfHasAce(simpleSum);
	}

	private Score simplySumScores() {
		Score score = Score.zero();

		for (Card card : hand) {
			score = score.add(card.getScore());
		}
		return score;
	}

	private Score maximizeSimpleSumIfHasAce(Score simpleSum) {
		if (simpleSum.isUnder(MAX_SCORE_TO_MAXIMIZE) && hasAce()) {
			return simpleSum.add(Score.of(ADDING_SCORE_TO_MAXIMIZE));
		}
		return simpleSum;
	}

	private boolean hasAce() {
		return hand.stream().anyMatch(Card::isAce);
	}

	public boolean isBlackjack() {
		return hasOnlyTwoCards() &&	computeScore().equals(Score.of(MAX_SCORE));
	}

	private boolean hasOnlyTwoCards() {
		return hand.size() == TWO;
	}

	public boolean isBust() {
		return computeScore().isOver(MAX_SCORE);
	}

	public boolean isBlackjackWin(Hand other) {
		return isBlackjack() && !other.isBlackjack();
	}

	public boolean isWinWithoutBlackjack(Hand other) {
		if (isBlackjack()) {
			return false;
		}
		if (isBust()) {
			return false;
		}
		if (other.isBust()) {
			return true;
		}
		return computeScore().isOver(other.computeScore());
	}

	public boolean isLose(Hand other) {
		if (!isBlackjack() && other.isBlackjack()) {
			return true;
		}
		if (isBust()) {
			return true;
		}
		if (other.isBust()) {
			return false;
		}
		return computeScore().isUnder(other.computeScore());
	}

	public Boolean isDraw(Hand other) {
		if (isBlackjack() ^ other.isBlackjack()) {
			return false;
		}
		return computeScore().equals(other.computeScore());
	}

	public boolean isUnder(int score) {
		return computeScore().isUnder(score);
	}

	public List<Card> getHand() {
		return Collections.unmodifiableList(hand);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hand hand1 = (Hand) o;
		return Objects.equals(hand, hand1.hand);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hand);
	}

	@Override
	public String toString() {
		return "Hand{" +
				"hand=" + hand +
				'}';
	}
}
