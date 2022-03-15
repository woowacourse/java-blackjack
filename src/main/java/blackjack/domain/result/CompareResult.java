package blackjack.domain.result;

import blackjack.domain.player.Gamer;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum CompareResult {

    WIN(new Keep(), CompareResult::isWin),
    DRAW(new Draw(), CompareResult::isDraw),
    LOSE(new Lose(), CompareResult::isLose);

    private final ResultStrategy resultStrategy;

    private final BiPredicate<Integer, Integer> predicate;

    CompareResult(final ResultStrategy resultStrategy, final BiPredicate<Integer, Integer> predicate) {
        this.resultStrategy = resultStrategy;
        this.predicate = predicate;
    }
    public static CompareResult findCompareResult(final int dealerResult, final int gamerResult) {
        return Arrays.stream(values())
                .filter((result) -> result.predicate.test(dealerResult, gamerResult))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }

    private static boolean isWin(Integer dealerResult, Integer gamerResult) {
        return (dealerResult > Gamer.LIMIT_GAMER_TOTAL_POINT &&
                gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT)
                || (dealerResult < gamerResult &&
                gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT);
    }

    private static boolean isDraw(Integer dealerResult, Integer gamerResult) {
        return dealerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
                gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
                dealerResult == gamerResult;
    }

    private static boolean isLose(Integer dealerResult, Integer gamerResult) {
        return dealerResult > gamerResult ||
                gamerResult > Gamer.LIMIT_GAMER_TOTAL_POINT;
    }

    public ResultStrategy getResult() {
        return resultStrategy;
    }
}
