package blackjack.domain.result;

import blackjack.domain.player.Gamer;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum CompareResult {

    WIN("승", (dealerResult, gamerResult) -> (dealerResult > Gamer.LIMIT_GAMER_TOTAL_POINT &&
            gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT)
            || (dealerResult < gamerResult &&
            gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT)),
    DRAW("무", (dealerResult, gamerResult) -> dealerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
            gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT &&
            dealerResult == gamerResult),
    LOSE("패", (dealerResult, gamerResult) -> dealerResult > gamerResult ||
            gamerResult > Gamer.LIMIT_GAMER_TOTAL_POINT);

    private final String result;
    private final BiPredicate<Integer, Integer> predicate;

    CompareResult(String result, BiPredicate<Integer, Integer> predicate) {
        this.result = result;
        this.predicate = predicate;
    }

    public static CompareResult findCompareResult(final int dealerResult, final int gamerResult) {
        return Arrays.stream(values())
                .filter((result) -> result.predicate.test(dealerResult, gamerResult))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }

    public String getResult() {
        return result;
    }
}
