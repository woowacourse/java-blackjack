package blackjack.domain.participant;

import blackjack.domain.betting.Betting;
import java.util.function.Function;

public enum Result {

    BLACK_JACK_WIN(betting -> (int) (betting.getValue() * 1.5)),
    WIN(Betting::getValue),
    DRAW(betting -> 0),
    LOSE(betting -> betting.getValue() * -1);

    public static Result show(final int targetScore, final int compareScore, final int pivot) {
        if (targetScore == pivot) {
            return checkPivotResult(compareScore, pivot);
        }
        return checkResult(targetScore, compareScore, pivot);
    }

    private static Result checkPivotResult(final int compareScore, final int pivot) {
        if (compareScore == pivot) {
            return Result.DRAW;
        }
        return Result.BLACK_JACK_WIN;
    }

    private static Result checkResult(final int targetScore, final int compareScore, final int pivot) {
        if (targetScore > pivot) {
            return Result.LOSE;
        }
        if (compareScore > pivot || compareScore < targetScore) {
            return Result.WIN;
        }
        if (compareScore > targetScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    private final Function<Betting, Integer> function;

    Result(final Function<Betting, Integer> function) {
        this.function = function;
    }

    public int calculateProfit(final Betting betting) {
        return function.apply(betting);
    }
}
