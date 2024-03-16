package blackjack.domain.bet;

import blackjack.domain.rule.state.State;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BetLeverage {
    BLACKJACK(1.5, BetLeverage::isBlackjackWin),
    WIN(1, BetLeverage::isWin),
    LOSE(-1, BetLeverage::isLose),
    PUSH(0, BetLeverage::isPush);

    private final double leverage;
    private final BiPredicate<State, State> condition;

    BetLeverage(final double leverage, final BiPredicate<State, State> condition) {
        this.leverage = leverage;
        this.condition = condition;
    }

    public static BetLeverage of(final State playerState, final State dealerState) {
        validateState(playerState, dealerState);
        return Arrays.stream(values())
                .filter(betStatus -> betStatus.condition.test(playerState, dealerState))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 점수입니다."));
    }

    private static boolean isBlackjackWin(final State playerState, final State dealerState) {
        return playerState.isBlackjack() && !dealerState.isBlackjack();
    }

    private static boolean isWin(final State playerState, final State dealerState) {
        return playerState.isNotBust() && (dealerState.isBust() || isScoreBigger(playerState, dealerState));
    }

    private static boolean isLose(final State playerState, final State dealerState) {
        return playerState.isBust() || (dealerState.isBlackjack() && !playerState.isBlackjack())
                || (!dealerState.isBust() && isScoreBigger(dealerState, playerState));
    }

    private static boolean isPush(final State playerState, final State dealerState) {
        return !dealerState.isBust() && !playerState.isBust() && isScoreSame(playerState, dealerState);
    }

    private static void validateState(final State playerState, final State dealerState) {
        if (playerState.isNotFinish() || dealerState.isNotFinish()) {
            throw new IllegalStateException("종료되지 않은 상태에서 결과를 계산할 수 없습니다.");
        }
    }

    private static boolean isScoreBigger(final State first, final State second) {
        return first.hands().calculateScore().compareTo(second.hands().calculateScore()) > 0;
    }

    private static boolean isScoreSame(final State first, final State second) {
        return first.hands().calculateScore().equals(second.hands().calculateScore());
    }

    public BetRevenue applyLeverage(final BetAmount betAmount) {
        return new BetRevenue(betAmount.multiply(leverage));
    }
}
