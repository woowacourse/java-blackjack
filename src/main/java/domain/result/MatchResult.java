package domain.result;

import java.util.Arrays;

import domain.result.resultStrategy.BustStrategy;
import domain.result.resultStrategy.DrawStrategy;
import domain.result.resultStrategy.LoseStrategy;
import domain.result.resultStrategy.MatchResultStrategy;
import domain.result.resultStrategy.WinStrategy;

public enum MatchResult {
	BUST(new BustStrategy()),
	WIN(new WinStrategy()),
	DRAW(new DrawStrategy()),
	LOSE(new LoseStrategy()),
	BLACKJACK(new BustStrategy());

	private final MatchResultStrategy matchResultStrategy;

	MatchResult(MatchResultStrategy matchResultStrategy) {
		this.matchResultStrategy = matchResultStrategy;
	}

	public static MatchResult of(int playerScore, int dealerScore) {
		return Arrays.stream(MatchResult.values())
			.filter(x -> x.matchResultStrategy.matchResultPredicate(playerScore, dealerScore))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
	}

	public MatchResultStrategy getMatchResultStrategy() {
		return matchResultStrategy;
	}
}
