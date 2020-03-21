package domain.result;

import static domain.gamer.Gamer.*;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

import domain.gamer.Money;

public enum MatchResult {
	BUST((playerScore, dealerScore) -> playerScore >= BUST_NUMBER, money -> money.multiply(-1)),
	WIN((playerScore, dealerScore) -> playerScore > dealerScore, money -> money.multiply(1)),
	DRAW((playerScore, dealerScore) -> playerScore == dealerScore, money -> money.multiply(0)),
	LOSE((playerScore, dealerScore) -> playerScore < dealerScore, money -> money.multiply(-1)),
	BLACKJACK((playerScore, dealerScore) -> playerScore == BLACKJACK_NUMBER, money -> money.multiply(1.5));

	private final BiPredicate<Integer, Integer> matchResultStrategy;
	private final Function<Money, Money> calculateEarn;

	MatchResult(BiPredicate<Integer, Integer> matchResultStrategy,
		Function<Money, Money> calculateEarn) {
		this.matchResultStrategy = matchResultStrategy;
		this.calculateEarn = calculateEarn;
	}

	public static MatchResult of(int playerScore, int dealerScore) {
		return Arrays.stream(MatchResult.values())
			.filter(x -> x.matchResultStrategy.test(playerScore, dealerScore))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
	}

	public Function<Money, Money> getCalculateEarn() {
		return calculateEarn;
	}
}
