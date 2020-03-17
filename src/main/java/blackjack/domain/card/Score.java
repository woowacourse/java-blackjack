package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import blackjack.domain.exceptions.InvalidScoreException;

public class Score {
	static final int BUST_SCORE = 22;
	static final Score ZERO = new Score(0);
	static final Score ADDITIONAL_ACE_SCORE = new Score(10);
	private static final Map<Integer, Score> SCORE_CACHE = new HashMap<>();

	static {
		IntStream.range(1, BUST_SCORE)
			.forEach(score -> SCORE_CACHE.put(score, new Score(score)));
	}

	private final int score;

	private Score(int score) {
		validate(score);
		this.score = score;
	}

	private void validate(int score) {
		if (score < 0) {
			throw new InvalidScoreException(InvalidScoreException.INVALID);
		}
	}

	public static Score valueOf(int number) {
		return Optional.ofNullable(SCORE_CACHE.get(number))
			.orElse(new Score(number));
	}

	public static Score valueOf(Card card) {
		return Optional.ofNullable(card)
			.map(value -> Score.valueOf(value.getScore()))
			.orElseThrow(() -> new InvalidScoreException(InvalidScoreException.NULL));
	}

	public Score add(Score score) {
		return Optional.ofNullable(score)
			.map(value -> Score.valueOf(this.score + value.score))
			.orElseThrow(() -> new InvalidScoreException(InvalidScoreException.NULL));
	}

	public boolean isLowerThan(int score) {
		return this.score <= score;
	}

	public boolean isMoreThan(int score) {
		return this.score >= score;
	}

	public int getScore() {
		return score;
	}
}
