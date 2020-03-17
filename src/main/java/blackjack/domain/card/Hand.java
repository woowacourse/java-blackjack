package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Hand {
	private static final int MAX_SCORE_TO_MAXIMIZE = 12;
	private static final int ADDING_SCORE_TO_MAXIMIZE = 10;

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

	public int size() {
		return hand.size();
	}

	public Score getScore() {
		Score score = simpleSum();
		return maximizeIfHasAce(score);
	}

	private Score simpleSum() {
		Score score = Score.of(0);

		for (Card card : hand) {
			score = score.add(card.getScore());
		}
		return score;
	}

	private Score maximizeIfHasAce(Score score) {
		if (score.isUnder(MAX_SCORE_TO_MAXIMIZE) && hasAce()) {
			return score.add(Score.of(ADDING_SCORE_TO_MAXIMIZE));
		}
		return score;
	}

	private boolean hasAce() {
		return hand.stream().anyMatch(Card::isAce);
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
