package domain.result;

import domain.gamer.Gamer;
import domain.gamer.Money;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PlayerResult {
	BLACK_JACK_WIN("블랙잭", 0.5, (gamer, otherGamer) -> {
		return onlyPlayerBlackJack(gamer, otherGamer);
	}),
	WIN("승", 1, (gamer, otherGamer) -> {
		return gamer.isNotBlackJack() &&
				(canWinByScore(gamer, otherGamer) || onlyOtherGamerBust(gamer, otherGamer));
	}),
	DRAW("무", 0, (gamer, otherGamer) -> {
		return (gamer.isBlackJack() && otherGamer.isBlackJack()) ||
				isScoreEqual(gamer, otherGamer);
	}),
	LOSE("패", -1, (gamer, otherGamer) -> {
		return canWinByScore(otherGamer, gamer) || gamer.isBust();
	});

	private final String result;
	private final double times;
	private final BiFunction<Gamer, Gamer, Boolean> expression;

	PlayerResult(String result, double times, BiFunction<Gamer, Gamer, Boolean> expression) {
		this.result = result;
		this.times = times;
		this.expression = expression;
	}

	private static boolean canWinByScore(Gamer gamer, Gamer otherGamer) {
		return eachNotBust(gamer, otherGamer) && gamer.hasBiggerScoreThan(otherGamer);
	}

	private static boolean onlyOtherGamerBust(Gamer gamer, Gamer otherGamer) {
		return gamer.isNotBust() && otherGamer.isBust();
	}

	private static boolean isScoreEqual(Gamer gamer, Gamer otherGamer) {
		return eachNotBust(gamer, otherGamer) && gamer.hasEqualScoreWith(otherGamer);
	}

	private static boolean eachNotBust(Gamer gamer, Gamer otherGamer) {
		return gamer.isNotBust() && otherGamer.isNotBust();
	}

	public static PlayerResult of(Gamer gamer, Gamer otherGamer) {
		return Arrays.stream(PlayerResult.values())
				.filter(result -> result.expression.apply(gamer, otherGamer))
				.findFirst()
				.orElseThrow(() -> new AssertionError("Result 중 반드시 하나가 반환되어야합니다."));
	}

	private static boolean onlyPlayerBlackJack(Gamer gamer, Gamer otherGamer) {
		return gamer.isBlackJack() && otherGamer.isNotBlackJack();
	}

	public double calculateProfit(Money money) {
		return money.multiply(this.times);
	}

	public String getResult() {
		return this.result;
	}
}