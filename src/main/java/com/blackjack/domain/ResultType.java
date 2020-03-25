package com.blackjack.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ResultType {
	BLACKJACK_WIN(Score::isBlackjack, (compareResult) -> compareResult > 0, 1.5),
	WIN((compareResult) -> compareResult > 0, 1),
	DRAW((compareResult) -> compareResult == 0, 0),
	LOSE((compareResult) -> compareResult < 0, -1);

	private Predicate<Score> blackjackMatch;
	private Predicate<Integer> match;
	private double profitRate;

	ResultType(Predicate<Integer> match, double profitRate) {
		this((playerScore) -> true, match, profitRate);
	}

	ResultType(Predicate<Score> blackjackMatch, Predicate<Integer> match, double profitRate) {
		this.blackjackMatch = blackjackMatch;
		this.match = match;
		this.profitRate = profitRate;
	}

	public static ResultType of(Score playerScore, Score dealerScore) {
		int compareResult = playerScore.compareTo(dealerScore);
		return Arrays.stream(values())
				.filter(resultType -> resultType.isMatch(compareResult))
				.filter(resultType -> resultType.isBlackjack(playerScore))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	private boolean isBlackjack(Score playerScore) {
		return blackjackMatch.test(playerScore);
	}

	private boolean isMatch(int compareResult) {
		return match.test(compareResult);
	}

	public double getProfitRate() {
		return profitRate;
	}
}