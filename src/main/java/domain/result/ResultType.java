package domain.result;

import java.util.Arrays;
import java.util.function.BiFunction;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

public enum ResultType {
	WIN("승", "패", (gamer, otherGamer) -> {
		return (!gamer.isBust() && (otherGamer.isBust() || gamer.isBiggerThan(otherGamer)));
	}),
	DRAW("무", "무", (gamer, otherGamer) -> {
		return !gamer.isBust() && !otherGamer.isBust() && gamer.isEqualTo(otherGamer);
	}),
	LOSE("패", "승", (gamer, otherGamer) -> {
		return gamer.isBust() || gamer.isLowerThan(otherGamer);
	});

	private final String result;
	private final String reversed;
	private final BiFunction<Gamer, Gamer, Boolean> expression;

	ResultType(String result, String reversed,
		BiFunction<Gamer, Gamer, Boolean> expression) {
		this.result = result;
		this.reversed = reversed;
		this.expression = expression;
	}

	public static ResultType of(Gamer gamer, Gamer otherGamer) {
		return Arrays.stream(ResultType.values())
			.filter(result -> result.expression.apply(gamer, otherGamer))
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
