package com.blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score implements Comparable<Score> {
	private static final int MIN_SCORE = 0;
	private static final int MAX_SCORE = 21;

	private static final Map<Integer, Score> SCORE_CACHE = new HashMap<>();

	static {
		for (int i = MIN_SCORE; i <= MAX_SCORE; i++) {
			SCORE_CACHE.put(i, new Score(i));
		}
	}

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	static Score of(int score) {
		validateBound(score);
		return SCORE_CACHE.get(score);
	}

	private static void validateBound(int score) {
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
