package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import blackjack.domain.exceptions.InvalidScoreException;

public class Score {
	static final int BUST_SCORE = 22;
	static final Score ZERO = new Score(0);
	private static final Map<Integer, Score> CACHE = new HashMap<>();
	private static final int ADDITIONAL_ACE_SCORE = 10;

	static {
		IntStream.range(Symbol.ACE.getScore(), BUST_SCORE)
			.forEach(score -> CACHE.put(score, new Score(score)));
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
		Score score = CACHE.get(number);

		if (Objects.isNull(score)) {
			score = new Score(number);
		}
		return score;
	}

	public static Score valueOf(Card card) {
		if (Objects.isNull(card)) {
			throw new InvalidScoreException(InvalidScoreException.NULL);
		}
		return Score.valueOf(card.getScore());
	}

	public Score add(Card card) {
		if (Objects.isNull(card)) {
			throw new InvalidScoreException(InvalidScoreException.NULL);
		}

		int calculatedScore = score + card.getScore();

		if (card.isAce() && isNotBustFromBigAceWith(calculatedScore)) {
			calculatedScore += ADDITIONAL_ACE_SCORE;
		}
		return Score.valueOf(calculatedScore);
	}

	private boolean isNotBustFromBigAceWith(int calculatedScore) {
		return calculatedScore + ADDITIONAL_ACE_SCORE < BUST_SCORE;
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
