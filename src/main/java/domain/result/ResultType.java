package domain.result;

import domain.gamer.Gamer;
import domain.gamer.Money;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum ResultType {
	BLACK_JACK("블랙잭", 0.5, (gamer, otherGamer) -> {
		return isBlackJack(gamer) && isNotBlackJack(otherGamer);
	}),
	WIN("승", 1, (gamer, otherGamer) -> {
		return isNotBlackJack(gamer) && (!gamer.isBust() && (otherGamer.isBust() || gamer.isBiggerThan(otherGamer)));
	}),
	DRAW("무", 0, (gamer, otherGamer) -> {
		return (isBlackJack(gamer) && isBlackJack(otherGamer)) ||
				(!gamer.isBust() && !otherGamer.isBust() && gamer.isEqualTo(otherGamer));
	}),
	LOSE("패", -1, (gamer, otherGamer) -> {
		return gamer.isBust() || gamer.isLowerThan(otherGamer);
	});

	private static final Map<ResultType, ResultType> resultToReverse;

	private final String result;
	private final double times;
	private final BiFunction<Gamer, Gamer, Boolean> expression;

	ResultType(String result, double times, BiFunction<Gamer, Gamer, Boolean> expression) {
		this.result = result;
		this.times = times;
		this.expression = expression;
	}

	static {
		resultToReverse = new HashMap<>();
		resultToReverse.put(WIN, LOSE);
		resultToReverse.put(DRAW, DRAW);
		resultToReverse.put(LOSE, WIN);
	}

	public static ResultType of(Gamer gamer, Gamer otherGamer) {
		return Arrays.stream(ResultType.values())
				.filter(result -> result.expression.apply(gamer, otherGamer))
				.findFirst()
				.orElseThrow(() -> new AssertionError("ResultType 중 반드시 하나가 반환되어야합니다."));
	}

	private static boolean isBlackJack(Gamer gamer) {
		return gamer.hasTwoCards() && gamer.getScore().isEqualTo(Score.BLACKJACK);
	}

	private static boolean isNotBlackJack(Gamer gamer) {
		return !isBlackJack(gamer);
	}

	public double calculateProfit(Money money) {
		return money.multiply(this.times);
	}

	public ResultType reverse() {
		return resultToReverse.get(this);
	}

	public String getResult() {
		return this.result;
	}
}