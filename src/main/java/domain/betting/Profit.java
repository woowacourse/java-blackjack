package domain.betting;

import domain.game.Result;
import java.util.Arrays;
import java.util.function.Predicate;

public enum Profit {

    WIN_PROFIT(Result::isPlayerWin, 1.0),
    LOSS_PROFIT(Result::isDealerWin, -1.0),
    PUSH_PROFIT(Result::isPush, 0),
    BALCK_JACK_PROFIT(Result::isPlayerBlackJack, 1.5);

    private final Predicate<Result> judgeProfit;
    private final double rate;

    Profit(final Predicate<Result> judgeProfit, final double some) {
        this.judgeProfit = judgeProfit;
        this.rate = some;
    }

    public static Profit of(final Result result) {
        return Arrays.stream(Profit.values())
                .filter(value -> value.judgeProfit.test(result))
                .findAny()
                .orElseThrow();

    }

    public int getMoney(final Bet bet) {
        return (int) (bet.getMoney() * rate);
    }
}
