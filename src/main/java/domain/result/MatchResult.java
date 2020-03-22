package domain.result;

import static domain.gamer.Gamer.*;

import java.util.Arrays;

import domain.result.resultStrategy.BustStrategy;
import domain.result.resultStrategy.DrawStrategy;
import domain.result.resultStrategy.LoseStrategy;
import domain.result.resultStrategy.MatchResultStrategy;
import domain.result.resultStrategy.WinStrategy;

public enum MatchResult {
	BUST(new BustStrategy(), -1),
	WIN(new WinStrategy(), 1),
	DRAW(new DrawStrategy(), 0),
	LOSE(new LoseStrategy(), -1),
	BLACKJACK((playerScore, dealerScore) -> playerScore == BLACKJACK_NUMBER, 1.5);

	private final MatchResultStrategy matchResultStrategy;
	private final double moneyRatio;

	MatchResult(MatchResultStrategy matchResultStrategy, double moneyRatio) {
		this.matchResultStrategy = matchResultStrategy;
		this.moneyRatio = moneyRatio;
	}

	public static MatchResult of(int playerScore, int dealerScore) {
		return Arrays.stream(MatchResult.values())
			.filter(x -> x.matchResultStrategy.matchResultPredicate(playerScore, dealerScore))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
	}

	public double getMoneyRatio() {
		return moneyRatio;
	}
}
