package domain.betting;

import domain.game.Result;
import java.util.Arrays;
import java.util.function.Predicate;

public enum ProfitRate {

    WIN_PROFIT(Result::isPlayerWin, 1.0),
    LOSS_PROFIT(Result::isDealerWin, -1.0),
    PUSH_PROFIT(Result::isPush, 0),
    BLACK_JACK_PROFIT(Result::isPlayerBlackJack, 1.5);

    private final Predicate<Result> judgeProfit;
    private final double rate;

    ProfitRate(final Predicate<Result> judgeProfit, final double rate) {
        this.judgeProfit = judgeProfit;
        this.rate = rate;
    }

    public static ProfitRate from(final Result result) {
        return Arrays.stream(ProfitRate.values())
                .filter(value -> value.judgeProfit.test(result))
                .findAny()
                .orElseThrow();

    }

    public double get() {
        return rate;
    }
}
