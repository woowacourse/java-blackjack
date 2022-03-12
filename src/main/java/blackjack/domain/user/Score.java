package blackjack.domain.user;

import static blackjack.domain.exceptionMessages.UserExceptionMessage.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Score {
	public static final int INITIAL_SCORE = 0;
	public static final int ELEVEN_ACE_SCORE = 11;
	public static final int MAX_SCORE = 21;
	private static final int HIT_THRESHOLD = 17;
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

	public Score addBy(final int number) {
		return Score.from(this.score + number);
	}

	public boolean isBiggerThan(final Score otherScore) {
		return this.score > otherScore.score;
	}

	public Score setToMinusOne() {
		return Score.from(-1);
	}

	public int getScore() {
		return score;
	}

	public boolean isBustScore() {
		return this.score > MAX_SCORE;
	}

	public boolean hasBustState() {
		return this.score < 0;
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

	public boolean isHit() {
		return this.score < HIT_THRESHOLD;
	}
}
