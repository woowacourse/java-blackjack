package domain.result;

import java.util.Objects;

import domain.card.Hand;

public class Score {
	private static final int BLACKJACK_SCORE = 21;
	public static final Score BLACKJACK = Score.of(BLACKJACK_SCORE);
	private static final int TEN = 10;

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score of(int score) {
		return new Score(score);
	}

	public static Score from(Hand hand) {
		int rawScore = hand.calculate();
		if (hand.hasAce() && rawScore + TEN <= BLACKJACK_SCORE) {
			return new Score(rawScore + TEN);
		}
		return new Score(rawScore);
	}

	public boolean isBiggerThan(Score other) {
		return this.score > other.score;
	}

	public boolean isEqualTo(Score other) {
		return this.score == other.score;
	}

	public boolean isLowerThan(Score other) {
		return this.score < other.score;
	}

	public boolean isBust() {
		return this.score > BLACKJACK_SCORE;
	}

	public int getScore() {
		return score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Score score1 = (Score)o;
		return score == score1.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}
}
