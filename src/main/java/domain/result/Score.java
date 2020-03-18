package domain.result;

import java.util.Objects;

import domain.gamer.Gamer;

public class Score {
	private static final int BLACKJACK_SCORE = 21;
	private static final int TEN = 10;
	public static final Score BLACKJACK = Score.from(BLACKJACK_SCORE);

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score from(int score) {
		return new Score(score);
	}

	public static Score from(Gamer gamer) {
		int rawScore = gamer.calculateCardSum();
		if (gamer.hasAce() && rawScore + TEN <= BLACKJACK_SCORE) {
			return Score.from(rawScore + TEN);
		}
		return Score.from(rawScore);
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
