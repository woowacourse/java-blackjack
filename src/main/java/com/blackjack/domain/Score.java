package com.blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Score {
	private static final String SCORE_OUT_OF_RANGE_MESSAGE = "점수의 범위를 벗어났습니다.";
	private static final int BUST_SCORE = 0;
	private static final int BLACKJACK_SCORE = 21;
	private static final Map<Integer, Score> SCORE_CACHE = new HashMap<>();

	static {
		for (int score = BUST_SCORE; score <= BLACKJACK_SCORE; ++score) {
			SCORE_CACHE.put(score, new Score(score));
		}
	}

	private final int score;

	private Score(int score) {
		validateBounds(score);
		this.score = score;
	}

	public static Score valueOf(int value) {
		return Optional.ofNullable(SCORE_CACHE.get(value))
				.orElseThrow(() -> new IllegalArgumentException(SCORE_OUT_OF_RANGE_MESSAGE));
	}

	private void validateBounds(int score) {
		if (score < BUST_SCORE) {
			throw new IllegalArgumentException(SCORE_OUT_OF_RANGE_MESSAGE);
		}
	}

	public boolean isLowerThan(int drawCondition) {
		return score < drawCondition;
	}

	public boolean isBust() {
		return score == BUST_SCORE;
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

	public ResultType compareTo(Score that) {
		if (score > that.score) {
			return ResultType.WIN;
		}
		if (score < that.score) {
			return ResultType.LOSE;
		}
		return ResultType.DRAW;
	}

	@Override
	public String toString() {
		return String.valueOf(score);
	}
}
