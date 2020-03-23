package blackjack.domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

import blackjack.domain.exceptions.InvalidHandTypeException;

public enum HandType {
	BLACKJACK(HandType::isBlackjack),
	BUST(HandType::isBust),
	NORMAL(HandType::isNormal);

	private final BiPredicate<Score, Boolean> judgeHandType;

	HandType(BiPredicate<Score, Boolean> judgeHandType) {
		this.judgeHandType = judgeHandType;
	}

	public static HandType of(Score score, boolean isInitialDealtSize) {
		validate(score);
		return Arrays.stream(values())
			.filter(handType -> handType.judgeHandType.test(score, isInitialDealtSize))
			.findFirst()
			.orElseThrow(() -> new InvalidHandTypeException(InvalidHandTypeException.NULL));
	}

	private static void validate(Score score) {
		if (Objects.isNull(score)) {
			throw new InvalidHandTypeException(InvalidHandTypeException.NULL);
		}
	}

	private static boolean isBlackjack(Score score, boolean isInitialDealtSize) {
		return score.isEqual(Score.BLACKJACK_JUDGEMENT_SCORE) && isInitialDealtSize;
	}

	private static boolean isBust(Score score, boolean isInitialDealtSize) {
		return score.isMoreThan(Score.BUST_SCORE);
	}

	private static boolean isNormal(Score score, boolean isInitialDealtSize) {
		return !isBlackjack(score, isInitialDealtSize) && !isBust(score, isInitialDealtSize);
	}

	public boolean isBlackjack() {
		return this.equals(BLACKJACK);
	}

	public boolean isBust() {
		return this.equals(BUST);
	}
}
