package domain.gamer;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum MatchResult {
	BUST("버스트", (playerScore, dealerScore) -> playerScore > 21,
		Money::reversion),
	WIN("승", (playerScore, dealerScore) -> playerScore > dealerScore || dealerScore > 21,
		bettingMoney -> bettingMoney),
	DRAW("무", Integer::equals,
		Money::getZeroMoney),
	LOSE("패", (playerScore, dealerScore) -> playerScore < dealerScore || playerScore > 21,
		Money::reversion),
	BLACKJACK("블랙잭", (playerScore, dealerScore) -> playerScore == 21,
		bettingMoney -> bettingMoney.multiply(Constants.BLACKJACK_MAGNIFICATION));

	private final String initial;
	private final BiPredicate<Integer, Integer> matchResultPredicate;
	private final Function<Money, Money> earnCalculator;

	MatchResult(String initial,
		BiPredicate<Integer, Integer> matchResultPredicate,
		Function<Money, Money> earnCalculator) {
		this.initial = initial;
		this.matchResultPredicate = matchResultPredicate;
		this.earnCalculator = earnCalculator;
	}

	public static MatchResult of(int playerScore, int dealerScore) {
		return Arrays.stream(MatchResult.values())
			.filter(x -> x.matchResultPredicate.test(playerScore, dealerScore))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
	}

	public Function<Money, Money> getEarnCalculator() {
		return earnCalculator;
	}

	private static class Constants {
		public static final double BLACKJACK_MAGNIFICATION = 1.5;
	}

}
