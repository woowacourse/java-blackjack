package domain.result;

import domain.gamer.Gamer;

import java.util.Objects;

public class Score {
	private static final int TEN = 10;
	private static final int BLACKJACK_SCORE = 21;

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score from(int score) {
		return new Score(score);
	}

	public static Score from(Gamer gamer) {
		int rawScore = gamer.sumOfCards();
		if (gamer.hasAce() && rawScore + TEN <= BLACKJACK_SCORE) {
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
		Score score1 = (Score) o;
		return score == score1.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}
}
