package com.blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score implements Comparable<Score> {
	private static final int BUSTED = 0;
	private static final int BUST_THRESHOLD = 21;

	private static final Map<Integer, Score> SCORE_CACHE = new HashMap<>();

	static {
		for (int i = BUSTED; i <= BUST_THRESHOLD; i++) {
			SCORE_CACHE.put(i, new Score(i));
		}
	}

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	static Score of(int score) {
		if (score > BUST_THRESHOLD) {
			return SCORE_CACHE.get(BUSTED);
		}
		return SCORE_CACHE.get(score);
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
