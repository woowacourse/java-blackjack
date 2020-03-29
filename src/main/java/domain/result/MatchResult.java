package domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import domain.betting.FinalMoney;
import domain.result.score.DealerFinalScore;
import domain.result.score.PlayerFinalScore;
import domain.user.Player;

public enum MatchResult {
	BLACKJACK_WIN(Referee::isPlayerBlackjackWin, 2.5),
	WIN(Referee::isPlayerWin, 2),
	DRAW(Referee::isPlayerDraw, 1),
	LOSE(Referee::isPlayerLose, 0);

	private static final String NO_SUCH_MESSAGE = "찾을 수 없는 경우입니다.";

	private final BiPredicate<PlayerFinalScore, DealerFinalScore> resultCondition;
	private final double profitRate;

	MatchResult(BiPredicate<PlayerFinalScore, DealerFinalScore> resultCondition, double profitRate) {
		this.resultCondition = resultCondition;
		this.profitRate = profitRate;
	}

	public static MatchResult findMatchResult(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return Arrays.stream(values())
			.filter(result -> result.resultCondition.test(playerScore, dealerScore))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NO_SUCH_MESSAGE));
	}

	public FinalMoney makeMoneyResult(Player player) {
		return new FinalMoney(player.calculateProfit(this.profitRate));
	}

}
