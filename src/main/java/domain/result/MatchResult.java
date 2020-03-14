package domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

import domain.user.Dealer;
import domain.user.Player;

public enum MatchResult {
	WIN("승", MatchResult::isPlayerWin),
	DRAW("무", MatchResult::isPlayerDraw),
	LOSE("패", MatchResult::isPlayerLose);

	private static final String ILLEGAL_RESULT_MESSAGE = "예측 불가능한 결과입니다.";
	private static final String PLAYER_AND_DEALER_NULL_EXCEPTION_MESSAGE = "객체에 NULL 값이 들어갈 수 없습니다..";

	private final String matchResult;
	private final BiPredicate<Player, Dealer> resultCondition;

	MatchResult(String matchResult, BiPredicate<Player, Dealer> resultCondition) {
		this.matchResult = matchResult;
		this.resultCondition = resultCondition;
	}

	public static MatchResult calculatePlayerMatchResult(Player player, Dealer dealer) {
		validateNull(player, dealer);
		return Arrays.stream(values())
			.filter(result -> result.resultCondition.test(player, dealer))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_RESULT_MESSAGE));
	}

	private static void validateNull(Player player, Dealer dealer) {
		if (Objects.isNull(player) || Objects.isNull(dealer)) {
			throw new NullPointerException(PLAYER_AND_DEALER_NULL_EXCEPTION_MESSAGE);
		}
	}

	public String getMatchResult() {
		return matchResult;
	}

	public MatchResult swtichWinAndLose() {
		if (this == MatchResult.WIN) {
			return MatchResult.LOSE;
		}
		if (this == MatchResult.LOSE) {
			return MatchResult.WIN;
		}
		return this;
	}

	private static boolean isPlayerWin(Player player, Dealer dealer) {
		boolean isNotPlayerBust = !player.isBust();
		boolean isPlayerOnlyBlackjack = player.isBlackjack() && !dealer.isBlackjack();
		boolean isPlayerScoreHigherThanDealers = player.hasHigherScoreThan(dealer);
		boolean isDealerBust = dealer.isBust();

		return isNotPlayerBust && (isPlayerOnlyBlackjack || isPlayerScoreHigherThanDealers || isDealerBust);
	}

	private static boolean isPlayerDraw(Player player, Dealer dealer) {
		return dealer.isBlackjack() && player.isBlackjack();
	}

	private static boolean isPlayerLose(Player player, Dealer dealer) {
		boolean isPlayerBust = player.isBust();
		boolean isDealerOnlyBlackjack = !player.isBlackjack() && dealer.isBlackjack();
		boolean isNotPlayerScoreHigherThanDealers = !player.hasHigherScoreThan(dealer);

		return isPlayerBust || isDealerOnlyBlackjack || isNotPlayerScoreHigherThanDealers;
	}
}
