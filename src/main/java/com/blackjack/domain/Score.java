package com.blackjack.domain;

import java.util.Objects;

public class Score implements Comparable<Score> {
	private static final int MIN_SCORE = 1;
	private static final int MAX_SCORE = 30;

	private final int score;

	public Score(int score) {
		validateBounds(score);
		this.score = score;
	}

	private void validateBounds(int score) {
		if (score < MIN_SCORE || score > MAX_SCORE) {
			throw new IllegalArgumentException("점수의 범위를 벗어났습니다.");
		}
	}

	public boolean isLowerThan(int drawCondition) {
		return score <= drawCondition;
	}

	@Override
	public int compareTo(Score o) {
		return Integer.compare(score, o.score);
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
