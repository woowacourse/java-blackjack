package domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

import domain.user.BettingMoney;
import domain.user.User;

public enum MatchResult {
	BLACKJACK_WIN(MatchResult::isPlayerBlackjackWin, 1.5),
	WIN(MatchResult::isPlayerWin, 1.0),
	DRAW(MatchResult::isPlayerDraw, 0),
	LOSE(MatchResult::isPlayerLose, -1.0);

	private static final String ILLEGAL_RESULT_MESSAGE = "예측 불가능한 결과입니다.";
	private static final String PLAYER_AND_DEALER_NULL_EXCEPTION_MESSAGE = "객체에 NULL 값이 들어갈 수 없습니다..";

	private final BiPredicate<User, User> resultCondition;
	private final double prizeFactor;

	MatchResult(BiPredicate<User, User> resultCondition, double prizeFactor) {
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

	public int calculatePrize(BettingMoney playerBettingMoney) {
		return playerBettingMoney.multiply(prizeFactor);
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
}
