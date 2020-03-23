package domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

import domain.user.Dealer;
import domain.user.Money;
import domain.user.Player;

public enum MatchResult {
	BLACKJACK_WIN(MatchResult::isPlayerBlackjackWin, 1.5),
	WIN(MatchResult::isPlayerWin, 1.0),
	DRAW(MatchResult::isPlayerDraw, 0),
	LOSE(MatchResult::isPlayerLose, -1.0);

	private static final String ILLEGAL_RESULT_MESSAGE = "예측 불가능한 결과입니다.";
	private static final String PLAYER_AND_DEALER_NULL_EXCEPTION_MESSAGE = "객체에 NULL 값이 들어갈 수 없습니다..";

	private final BiPredicate<Player, Dealer> resultCondition;
	private final double prizeFactor;

	MatchResult(BiPredicate<Player, Dealer> resultCondition, double prizeFactor) {
		this.resultCondition = Objects.requireNonNull(resultCondition);
		this.prizeFactor = prizeFactor;
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

	public Prize calculatePrize(Money playerMoney) {
		Prize prize = Prize.valueOf(playerMoney.getMoney());
		return prize.multiply(prizeFactor);
	}

	private static boolean isPlayerBlackjackWin(Player player, Dealer dealer) {
		return player.isBlackjack() && !dealer.isBlackjack();
	}

	private static boolean isPlayerWin(Player player, Dealer dealer) {
		boolean isNotPlayerBust = !player.isBust();
		boolean areBothNotBlackjack = !player.isBlackjack() && !dealer.isBlackjack();
		boolean isPlayerScoreHigherThanDealers = player.hasHigherScoreThan(dealer);
		boolean isDealerBust = dealer.isBust();

		return isNotPlayerBust && areBothNotBlackjack && (isPlayerScoreHigherThanDealers || isDealerBust);
	}

	private static boolean isPlayerDraw(Player player, Dealer dealer) {
		boolean areBothBlackjack = dealer.isBlackjack() && player.isBlackjack();
		boolean areBothNotBlackjack = !dealer.isBlackjack() && !player.isBlackjack();
		boolean areBothNotBust = !dealer.isBust() && !player.isBust();
		boolean hasSameScore = player.hasSameScoreWith(dealer);

		return areBothBlackjack || (areBothNotBust && areBothNotBlackjack && hasSameScore);
	}

	private static boolean isPlayerLose(Player player, Dealer dealer) {
		boolean isPlayerBust = player.isBust();
		boolean isDealerOnlyBlackjack = !player.isBlackjack() && dealer.isBlackjack();
		boolean isNotPlayerScoreHigherThanDealers = player.hasLowerScoreThan(dealer);

		return isPlayerBust || isDealerOnlyBlackjack || isNotPlayerScoreHigherThanDealers;
	}
}
