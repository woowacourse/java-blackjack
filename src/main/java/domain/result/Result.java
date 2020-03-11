package domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
	WIN("승", (myScore, otherScore) -> myScore > otherScore),
	DRAW("무", Integer::equals),
	LOSE("패", (myScore, otherScore) -> myScore < otherScore);

	private String result;
	private BiFunction<Integer, Integer, Boolean> expression;

	Result(String result,
		BiFunction<Integer, Integer, Boolean> expression) {
		this.result = result;
		this.expression = expression;
	}

	public static Result of(int myScore, int otherScore) {
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
