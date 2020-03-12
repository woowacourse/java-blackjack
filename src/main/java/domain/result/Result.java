package domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
	WIN("승", (myScore, otherScore) -> {
		return (!myScore.isBust() && otherScore.isBust()) || myScore.isBiggerThan(otherScore);
	}),
	DRAW("무", (myScore, otherScore) -> {
		return !myScore.isBust() && !otherScore.isBust() && myScore.isEqualTo(otherScore);
	}),
	LOSE("패", (myScore, otherScore) -> {
		return myScore.isBust() || myScore.isLowerThan(otherScore);
	});

	private final String result;
	private final BiFunction<Score, Score, Boolean> expression;

	Result(String result,
		BiFunction<Score, Score, Boolean> expression) {
		this.result = result;
		this.expression = expression;
	}

	public static Result of(Score myScore, Score otherScore) {
		return Arrays.stream(Result.values())
			.filter(result -> result.expression.apply(myScore, otherScore))
			.findFirst()
			.get();
	}

	public static Result reverse(Result result) {
		if (result == WIN) {
			return LOSE;
		}
		if (result == LOSE) {
			return WIN;
		}
		return DRAW;
	}

	public String getResult() {
		return result;
	}
}
