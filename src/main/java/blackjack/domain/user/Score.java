package blackjack.domain.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Score implements Comparable<Score> {
	private static final int BUST_STATE_SCORE = -1;
	public static final int INITIAL_SCORE = 0;
	public static final int ELEVEN_ACE_SCORE = 11;
	private static final int HIT_THRESHOLD = 17;
	public static final int BUST_THRESHOLD = 21;
	private static final Map<Integer, Score> scores = new HashMap<>();

	static {
		IntStream.rangeClosed(BUST_STATE_SCORE, BUST_THRESHOLD)
			.forEach(number -> scores.put(number, new Score(number)));
	}

	private final int score;

	private Score(final int score) {
		this.score = score;
	}

	public static Score from(final int score) {
		if (hasGreaterThanBustThreshold(score)) {
			return scores.get(BUST_STATE_SCORE);
		}
		return scores.get(score);
	}

	private static boolean hasGreaterThanBustThreshold(final int score) {
		return score > BUST_THRESHOLD;
	}

	public Score addBy(final int number) {
		return Score.from(this.score + number);
	}

	public int getScore() {
		return score;
	}

	public boolean hasBustState() {
		return this.score <= BUST_STATE_SCORE;
	}

	public boolean isHit() {
		return this.score < HIT_THRESHOLD;
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
	public int compareTo(Score o) {
		return Integer.compare(this.score, o.score);
	}
}
