package blackjack.domain.result;

import blackjack.domain.player.Gamer;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum CompareResult {

    WIN(new Keep(), (dealerResult, gamerResult) -> (dealerResult > Gamer.LIMIT_GAMER_TOTAL_POINT &&
            gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT)
            || (dealerResult < gamerResult &&
            gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT)),
    DRAW(new Draw(), (dealerResult, gamerResult) -> dealerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
            gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
            dealerResult == gamerResult),
    LOSE(new Lose(), (dealerResult, gamerResult) -> dealerResult > gamerResult ||
            gamerResult > Gamer.LIMIT_GAMER_TOTAL_POINT);

    private final Result result;
    private final BiPredicate<Integer, Integer> predicate;

    CompareResult(final Result result, final BiPredicate<Integer, Integer> predicate) {
        this.result = result;
        this.predicate = predicate;
    }

    public static CompareResult findCompareResult(final int dealerResult, final int gamerResult) {
        return Arrays.stream(values())
                .filter((result) -> result.predicate.test(dealerResult, gamerResult))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }

    public Result getResult() {
        return result;
    }
}
