package blackjack.domain.bet;

import blackjack.domain.rule.Score;
import blackjack.domain.rule.state.State;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BetLeverage {
    BLACKJACK(1.5,
            (playerState, dealerState) -> playerState.isBlackjack() && dealerState.isNotBlackjack()),
    WIN(1,
            (playerState, dealerState) -> playerState.isNotBust()
                    && (dealerState.isBust() || isScoreBigger(playerState, dealerState))),
    LOSE(-1,
            (playerState, dealerState) -> playerState.isBust()
                    || (dealerState.isBlackjack() && playerState.isNotBlackjack())
                    || (dealerState.isNotBust() && isScoreBigger(dealerState, playerState))),
    PUSH(0,
            (playerState, dealerState) -> dealerState.isNotBust() && playerState.isNotBust()
                    && isScoreSame(playerState, dealerState));


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

    private static void validateState(final State playerState, final State dealerState) {
        if (playerState.isNotFinish() || dealerState.isNotFinish()) {
            throw new IllegalStateException("종료되지 않은 상태에서 결과를 계산할 수 없습니다.");
        }
    }

    private static boolean isScoreBigger(final State first, final State second) {
        final Score firstScore = first.hands().calculateScore();
        final Score secondScore = second.hands().calculateScore();

        return firstScore.compareTo(secondScore) > 0;
    }

    private static boolean isScoreSame(final State first, final State second) {
        final Score firstScore = first.hands().calculateScore();
        final Score secondScore = second.hands().calculateScore();

        return firstScore.equals(secondScore);
    }

    public BetRevenue applyLeverage(final BetAmount batAmount) {
        return new BetRevenue(batAmount.multiply(leverage));
    }

}
