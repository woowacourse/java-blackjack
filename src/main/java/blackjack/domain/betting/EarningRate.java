package blackjack.domain.betting;

import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import static blackjack.domain.scoreboard.WinOrLose.*;
import static blackjack.domain.user.status.Status.BLACKJACK;

public enum EarningRate {
    WINNING_RATE(2.0,
            (user, winOrLose) -> user.isNotStatus(BLACKJACK) && winOrLose == WIN),
    WIN_WITH_BLACKJACK_RATE(2.5,
            (user, winOrLose) -> user.isSameStatus(BLACKJACK) && winOrLose == WIN),
    DRAW_RATE(1.0,
            (user, winOrLose) -> winOrLose == DRAW),
    LOSE_RATE(0,
            (user, winOrLose) -> winOrLose == LOSE);

    private static final List<EarningRate> EARNING_RATES = Arrays.asList(values());

    private final double rate;
    private final BiPredicate<User, WinOrLose> decider;

    EarningRate(double rate, BiPredicate<User, WinOrLose> decider) {
        this.rate = rate;
        this.decider = decider;
    }

    public static BettingMoney calculate(User user, WinOrLose winOrLose) {
        EarningRate earningRate = EARNING_RATES.stream()
                .filter(element -> element.decider.test(user, winOrLose))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못 된 경우의 수입니다."));
        return user.getBettingMoney().multiply(earningRate.rate);
    }

    public double getRate() {
        return rate;
    }
}
