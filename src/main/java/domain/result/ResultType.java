package domain.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

public enum ResultType {
	BLACK_JACK("블랙잭", (gamer, otherGamer) -> {
		return isBlackJack(gamer) && isNotBlackJack(otherGamer);
	}),
	WIN("승", (gamer, otherGamer) -> {
		return isNotBlackJack(gamer) && (!gamer.isBust() && (otherGamer.isBust() || gamer.isBiggerThan(otherGamer)));
	}),
	DRAW("무", (gamer, otherGamer) -> {
		return (isBlackJack(gamer) && isBlackJack(otherGamer)) ||
			(!gamer.isBust() && !otherGamer.isBust() && gamer.isEqualTo(otherGamer));
	}),
	LOSE("패", (gamer, otherGamer) -> {
		return gamer.isBust() || gamer.isLowerThan(otherGamer);
	});

	private static final Map<ResultType, ResultType> reversed;

	private final String result;
	private final BiFunction<Gamer, Gamer, Boolean> expression;

	ResultType(String result, BiFunction<Gamer, Gamer, Boolean> expression) {
		this.result = result;
		this.expression = expression;
	}

	static {
		reversed = new HashMap<>();
		reversed.put(WIN, LOSE);
		reversed.put(DRAW, DRAW);
		reversed.put(LOSE, WIN);
	}

	public static ResultType of(Gamer gamer, Gamer otherGamer) {
		return Arrays.stream(ResultType.values())
			.filter(result -> result.expression.apply(gamer, otherGamer))
			.findFirst()
			.orElseThrow(() -> new AssertionError("ResultType 중 반드시 하나가 반환되어야합니다."));
	}

	public static ResultType reverse(ResultType resultType) {
		return reversed.get(resultType);
	}

	private static boolean isBlackJack(Gamer gamer) {
		return gamer.hasTwoCards() && gamer.getScore().isEqualTo(Score.BLACKJACK);
	}

	private static boolean isNotBlackJack(Gamer gamer) {
		return !isBlackJack(gamer);
	}

	public String getResult() {
		return result;
	}
}
