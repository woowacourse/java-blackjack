package com.blackjack.domain;

import java.util.Objects;

public class Score implements Comparable<Score> {
	public static final int BLACKJACK_SCORE = 21;
	private static final int ACE_UPWARD_CONDITION_UPPER_BOUND = 11;
	private static final int ACE_UPWARD_SCORE = 10;
	private static final int MIN_SCORE = 0;
	private static final int MAX_SCORE = 30;

	private final int score;
	private final boolean isFirstDraw;

	public Score(int score) {
		this(score, false);
	}

	public Score(int score, boolean isFirstDraw) {
		validateBounds(score);
		this.score = score;
		this.isFirstDraw = isFirstDraw;
	}

	public static Score of(int score, boolean hasAce, boolean isFirstDraw) {
		return new Score(computeByAce(score, hasAce), isFirstDraw);
	}

	private static int computeByAce(int score, boolean hasAce) {
		if (hasAce && score <= ACE_UPWARD_CONDITION_UPPER_BOUND) {
			return score + ACE_UPWARD_SCORE;
		}
		return score;
	}

	private void validateBounds(int score) {
		if (score < MIN_SCORE || score > MAX_SCORE) {
			throw new IllegalArgumentException("점수의 범위를 벗어났습니다.");
		}
	}

	public boolean isLowerThan(int drawCondition) {
		return score < drawCondition;
	}

	public boolean isBust() {
		return score > BLACKJACK_SCORE;
	}

	public boolean isNotBust() {
		return !isBust();
	}

	public boolean isBlackjack() {
		return isFirstDraw && score == BLACKJACK_SCORE;
	}

	public boolean isNotBlackjack() {
		return !isBlackjack();
	}

	public int compareTo(Score that) {
		return Integer.compare(this.score, that.score);
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

	@Override
	public String toString() {
		return String.valueOf(score);
	}
}
