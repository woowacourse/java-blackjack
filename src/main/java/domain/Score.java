package domain;

import java.util.Objects;

public class Score {
	private static final int MIN = 0;
	private static final int ACE_BONUS = 10;
	private static final int BLACKJACK_SIZE = 2;
	private static final int BLACKJACK_SCORE = 21;

	public static final Score ZERO = new Score(MIN);

	private final int score;

	public Score(int score) {
		validate(score);
		this.score = score;
	}

	private void validate(int score) {
		if (score < MIN) {
			throw new IllegalArgumentException("점수는 0 이상이어야 합니다.");
		}
	}

	public int getValue() {
		return score;
	}

	public Score addAceBonusIfNotBust() {
		Score score = add(ACE_BONUS);
		if (score.isBust()) {
			return this;
		}
		return score;
	}

	public Score add(int point) {
		return new Score(score + point);
	}

	public boolean isBlackjack(int cardSize) {
		return cardSize == BLACKJACK_SIZE && score == BLACKJACK_SCORE;
	}

	public boolean isBust() {
		return score > BLACKJACK_SCORE;
	}

	public boolean isGreaterThan(int score) {
		return this.score >= score;
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
