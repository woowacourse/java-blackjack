package blackjack.domain.player.bet;

import blackjack.domain.rule.state.Blackjack;
import blackjack.domain.rule.state.Burst;
import blackjack.domain.rule.state.State;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BetStatus {
    LUCKY(1.5,
            (dealerState, playerState) -> isStartBlackjack(playerState) && isNotStartBlackjack(dealerState)),
    WIN(1,
            (dealerState, playerState) -> isNotBurst(playerState)
            && (isBurst(dealerState) || isScoreBigger(playerState, dealerState))),
    LOSE(-1,
            (dealerState, playerState) -> isBurst(playerState)
            || (isStartBlackjack(dealerState) && isNotStartBlackjack(playerState))
            || (isNotBurst(dealerState) && isScoreBigger(dealerState, playerState))),
    DRAW(0,
            (dealerState, playerState) -> isNotBurst(dealerState) && isNotBurst(playerState)
            && isScoreSame(playerState, dealerState));


    private final double leverage;

    private final BiPredicate<State, State> condition;

    BetStatus(final double leverage, final BiPredicate<State, State> condition) {
        this.leverage = leverage;
        this.condition = condition;
    }
    public static BetStatus of(final State dealerState, final State playerState) {
        return Arrays.stream(values())
                .filter(betStatus -> betStatus.condition.test(dealerState, playerState))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("잘못된 점수입니다."));
    }

    private static boolean isStartBlackjack(final State state) {
        return state instanceof Blackjack;
    }

    private static boolean isNotStartBlackjack(final State state) {
        return !isStartBlackjack(state);
    }

    private static boolean isBurst(final State state) {
        return state instanceof Burst;
    }

    private static boolean isNotBurst(final State state) {
        return !isBurst(state);
    }
    private static boolean isScoreBigger(final State first, final State second) {
        return first.getHands().isScoreBiggerThan(second.getHands());
    }

    private static boolean isScoreSame(final State first, final State second) {
        return first.getHands().isScoreSame(second.getHands());
    }

    public BetRevenue applyLeverage(final BetAmount batAmount) {
        return new BetRevenue(batAmount.multiply(leverage));
    }

}
