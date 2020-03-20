package blackjack.domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

import blackjack.domain.exceptions.InvalidScoreTypeException;

public enum ScoreType {
	BLACKJACK(ScoreType::isBlackjack),
	BUST(ScoreType::isBust),
	NORMAL(ScoreType::isNormal);

	private final BiPredicate<Score, Boolean> judgeScoreType;

	ScoreType(BiPredicate<Score, Boolean> judgeScoreType) {
		this.judgeScoreType = judgeScoreType;
	}

	public static ScoreType of(Score score, boolean isInitialDealtSize) {
		validate(score);
		return Arrays.stream(values())
			.filter(scoreType -> scoreType.judgeScoreType.test(score, isInitialDealtSize))
			.findFirst()
			.orElseThrow(() -> new InvalidScoreTypeException(InvalidScoreTypeException.NULL));
	}

	private static void validate(Score score) {
		if (Objects.isNull(score)) {
			throw new InvalidScoreTypeException(InvalidScoreTypeException.NULL);
		}
	}

	private static boolean isBlackjack(Score score, boolean isInitialDealtSize) {
		return score.isEqual(Score.BLACKJACK_JUDGEMENT_SCORE) && isInitialDealtSize;
	}

	private static boolean isBust(Score score, Boolean isInitialDealtSize) {
		return score.isMoreThan(Score.BUST_SCORE);
	}

	private static boolean isNormal(Score score, Boolean isInitialDealtSize) {
		return !isBlackjack(score, isInitialDealtSize) && !isBust(score, isInitialDealtSize);
	}

	public boolean isBlackjack() {
		return this.equals(BLACKJACK);
	}

	public boolean isBust() {
		return this.equals(BUST);
	}
}
