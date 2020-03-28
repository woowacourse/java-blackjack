package domain;

import java.util.Arrays;

import domain.money.BlackjackWinStrategy;
import domain.money.Money;
import domain.money.ProfitStrategy;
import domain.money.PushStrategy;
import domain.money.WinStrategy;

/**
 *   블랙잭 결과 매치에 대한 enum입니다.
 *
 *   @author ParkDooWon
 */
public enum MatchResult {
	WIN(true, false, new WinStrategy()),
	BLACKJACK_WIN(true, false, new BlackjackWinStrategy()),
	PUSH(false, true, new PushStrategy()),
	DEFEAT(false, false, (Money money) -> money.getValue() * -0.1);

	private boolean win;
	private boolean push;
	private ProfitStrategy profitStrategy;

	MatchResult(boolean win, boolean push, ProfitStrategy profitStrategy) {
		this.win = win;
		this.push = push;
		this.profitStrategy = profitStrategy;
	}

	public static MatchResult getMatchResult(boolean isWin, boolean isPush, boolean isBlackjack) {
		MatchResult matchResult = Arrays.stream(MatchResult.values())
			.filter(match -> match.win == isWin)
			.filter(match -> match.push == isPush)
			.findFirst()
			.orElse(DEFEAT);
		if (matchResult.equals(WIN) && isBlackjack) {
			matchResult = BLACKJACK_WIN;
		}
		return matchResult;
	}

	public ProfitStrategy getProfitStrategy() {
		return profitStrategy;
	}
}
