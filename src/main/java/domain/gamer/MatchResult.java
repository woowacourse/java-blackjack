package domain.gamer;

import java.util.Arrays;

import java.util.function.BiPredicate;
import java.util.function.Function;

public enum MatchResult {
	BUST((playerScore, dealerScore) -> playerScore >= Constants.BUST_NUMBER,
		Money::reversion),
	WIN((playerScore, dealerScore) -> playerScore > dealerScore || dealerScore >= Constants.BUST_NUMBER,
		bettingMoney -> bettingMoney),
	DRAW((playerScore, dealerScore) -> playerScore == dealerScore,
		Money::getZeroMoney),
	LOSE((playerScore, dealerScore) -> playerScore < dealerScore || playerScore >= Constants.BUST_NUMBER,
		Money::reversion),
	BLACKJACK((playerScore, dealerScore) -> playerScore == Constants.BLACKJACK_NUMBER,
		bettingMoney -> bettingMoney.multiply(Constants.BLACKJACK_MAGNIFICATION));

	private final BiPredicate<Integer, Integer> matchResultPredicate;
	private final Function<Money, Money> earnCalculator;

	MatchResult(BiPredicate<Integer, Integer> matchResultPredicate,
		Function<Money, Money> earnCalculator) {
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
		public static final int BUST_NUMBER = 22;
		public static final int BLACKJACK_NUMBER = 21;
	}
}
