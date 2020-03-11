package domain;

import java.util.Objects;

public class Score {
	public static final Score ZERO = new Score(0);
	private static final int ACE_BONUS = 10;
	private static final int CRITICAL_POINT = 21;

	private final int score;

	public Score(int score) {
		this.score = score;
	}

	public int getValue() {
		return score;
	}

	public Score add(int point) {
		return new Score(score + point);
	}

	public Score addAceBonusIfNotBust() {
		Score newScore = add(ACE_BONUS);
		if (newScore.isNotBust()) {
			return newScore;
		}
		return this;
	}

	public boolean isNotBust() {
		return score <= CRITICAL_POINT;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Score that = (Score)o;
		return score == that.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}
}
