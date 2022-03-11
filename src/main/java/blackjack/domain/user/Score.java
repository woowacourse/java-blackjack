package blackjack.domain.user;

import static blackjack.domain.exceptionMessages.UserExceptionMessage.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Score {
	private static final Map<Integer, Score> scores = new HashMap<>();

	static {
		IntStream.rangeClosed(-1, 32)
			.forEach(number -> scores.put(number, new Score(number)));
	}

	private final int score;

	private Score(final int score) {
		this.score = score;
	}

	public static Score from(final int score) {
		if (score < -1 || score > 32) {
			throw new IllegalArgumentException(SCORE_RANGE_EXCEPTION.getMessage());
		}
		return scores.get(score);
	}

	public Score addBy(int number) {
		return Score.from(this.score + number);
	}

	public boolean isBiggerThan(final int number) {
		return this.score > number;
	}

	public boolean isSmallerThan(final int number) {
		return this.score < number;
	}

	public Score setToMinusOne() {
		return Score.from(-1);
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
