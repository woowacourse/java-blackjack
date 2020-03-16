package domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import domain.result.score.DealerFinalScore;
import domain.result.score.PlayerFinalScore;

public enum MatchResult {
	WIN("승", Referee::isPlayerWin),
	DRAW("무", Referee::isPlayerDraw),
	LOSE("패", Referee::isPlayerLose);

	private static final String NO_SUCH_MESSAGE = "찾을 수 없는 경우입니다.";

	private final String matchResult;
	private final BiPredicate<PlayerFinalScore, DealerFinalScore> resultCondition;

	MatchResult(String matchResult, BiPredicate<PlayerFinalScore, DealerFinalScore> resultCondition) {
		this.matchResult = matchResult;
		this.resultCondition = resultCondition;
	}

	public static MatchResult findMatchResult(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return Arrays.stream(values())
			.filter(result -> result.resultCondition.test(playerScore, dealerScore))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NO_SUCH_MESSAGE));
	}

	public MatchResult reverseWinAndLose() {
		if (this == MatchResult.WIN) {
			return MatchResult.LOSE;
		}
		if (this == MatchResult.LOSE) {
			return MatchResult.WIN;
		}
		return this;
	}

	public String getMatchResult() {
		return matchResult;
	}
}
