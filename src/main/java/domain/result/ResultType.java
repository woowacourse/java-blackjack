package domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum ResultType {
	WIN("승", "패", (myScore, otherScore) -> {
		return (!myScore.isBust() && otherScore.isBust()) || myScore.isBiggerThan(otherScore);
	}),
	DRAW("무", "무", (myScore, otherScore) -> {
		return !myScore.isBust() && !otherScore.isBust() && myScore.isEqualTo(otherScore);
	}),
	LOSE("패", "승", (myScore, otherScore) -> {
		return myScore.isBust() || myScore.isLowerThan(otherScore);
	});

	private final String result;
	private final String reversed;
	private final BiFunction<Score, Score, Boolean> expression;

	ResultType(String result, String reversed,
		BiFunction<Score, Score, Boolean> expression) {
		this.result = result;
		this.reversed = reversed;
		this.expression = expression;
	}

	public static ResultType of(Score myScore, Score otherScore) {
		return Arrays.stream(ResultType.values())
			.filter(result -> result.expression.apply(myScore, otherScore))
			.findFirst()
			.get();
	}

	public static ResultType reverse(ResultType resultType) {
		return Arrays.stream(ResultType.values())
			.filter(each -> each.reversed.equals(resultType.result))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public String getResult() {
		return result;
	}
}
