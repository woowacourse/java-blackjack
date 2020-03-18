package domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

import domain.user.User;

public enum MatchResult {
	BLACKJACK_WIN("블랙잭승", MatchResult::isPlayerBlackjackWin, 1.5),
	WIN("승", MatchResult::isPlayerWin, 1.0),
	DRAW("무", MatchResult::isPlayerDraw, 0),
	LOSE("패", MatchResult::isPlayerLose, -1.0);

	private static final String ILLEGAL_RESULT_MESSAGE = "예측 불가능한 결과입니다.";
	private static final String PLAYER_AND_DEALER_NULL_EXCEPTION_MESSAGE = "객체에 NULL 값이 들어갈 수 없습니다..";

	private final String matchResult;
	private final BiPredicate<User, User> resultCondition;
	private final double prizeFactor;

	MatchResult(String matchResult, BiPredicate<User, User> resultCondition, double prizeFactor) {
		this.matchResult = matchResult;
		this.resultCondition = resultCondition;
		this.prizeFactor = prizeFactor;
	}

	public static MatchResult calculatePlayerMatchResult(User player, User dealer) {
		validateNull(player, dealer);
		return Arrays.stream(values())
			.filter(result -> result.resultCondition.test(player, dealer))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_RESULT_MESSAGE));
	}

	private static void validateNull(User player, User dealer) {
		if (Objects.isNull(player) || Objects.isNull(dealer)) {
			throw new NullPointerException(PLAYER_AND_DEALER_NULL_EXCEPTION_MESSAGE);
		}
	}

	public MatchResult switchWinAndLose() {
		if (this == MatchResult.WIN) {
			return MatchResult.LOSE;
		}
		if (this == MatchResult.LOSE) {
			return MatchResult.WIN;
		}
		return this;
	}

	private static boolean isPlayerBlackjackWin(User player, User dealer) {
		return player.isBlackjack() && !dealer.isBlackjack();
	}

	private static boolean isPlayerWin(User player, User dealer) {
		boolean isNotPlayerBust = !player.isBust();
		boolean bothPlayerAndDealerIsNotBlackjack = !player.isBlackjack() && !dealer.isBlackjack();
		boolean isPlayerScoreHigherThanDealers = player.hasHigherScoreThan(dealer);
		boolean isDealerBust = dealer.isBust();

		return isNotPlayerBust && bothPlayerAndDealerIsNotBlackjack && (isPlayerScoreHigherThanDealers || isDealerBust);
	}

	private static boolean isPlayerDraw(User player, User dealer) {
		return dealer.isBlackjack() && player.isBlackjack();
	}

	private static boolean isPlayerLose(User player, User dealer) {
		boolean isPlayerBust = player.isBust();
		boolean isDealerOnlyBlackjack = !player.isBlackjack() && dealer.isBlackjack();
		boolean isNotPlayerScoreHigherThanDealers = !player.hasHigherScoreThan(dealer);

		return isPlayerBust || isDealerOnlyBlackjack || isNotPlayerScoreHigherThanDealers;
	}

	public String getMatchResult() {
		return matchResult;
	}


}
