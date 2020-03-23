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

	public boolean isBlackJack() {
		return hasOnlyTwoCards() &&	computeScore().equals(Score.of(MAX_SCORE));
	}

	private boolean hasOnlyTwoCards() {
		return hand.size() == TWO;
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
