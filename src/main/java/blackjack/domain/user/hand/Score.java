package blackjack.domain.user.hand;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;

public class Score {
	public static final Score ZERO = new Score(0);
	private static final Map<Integer, Score> CACHE = new HashMap<>();
	private static final int ADDITIONAL_ACE_SCORE = 10;
	private static final int BUST_SCORE = 22;

	private final int score;

	static {
		IntStream.range(Symbol.ACE.getScore(), BUST_SCORE)
			.forEach(score -> CACHE.put(score, new Score(score)));
	}

	private Score(int score) {
		this.score = score;
	}

	public static Score valueOf(int number) {
		Score score = CACHE.get(number);

		if (Objects.isNull(score)) {
			score = new Score(number);
		}
		return score;
	}

	public static Score valueOf(Card card) {
		return CACHE.get(card.getSymbolValue());
	}

	public Score add(Card card) {
		int currentScore = score + card.getSymbolValue();

		if (card.isAce() && currentScore + ADDITIONAL_ACE_SCORE < BUST_SCORE) {
			return Score.valueOf(currentScore + ADDITIONAL_ACE_SCORE);
		}
		return Score.valueOf(currentScore);
	}

	public boolean isLowerThan(int score) {
		return this.score <= score;
	}

	public boolean isMoreThan(int score) {
		return this.score > score;
	}

	public boolean isMoreThan(Score score) {
		return this.score > score.score;
	}

	public int getScore() {
		return score;
	}
}
