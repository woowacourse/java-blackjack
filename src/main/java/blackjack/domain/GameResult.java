package blackjack.domain;

import blackjack.domain.player.Gamer;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    WIN("승", (dealerResult, gamerResult) -> gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT
        && (dealerResult < gamerResult || dealerResult > Gamer.LIMIT_GAMER_TOTAL_POINT)
    ),
    DRAW("무", (dealerResult, gamerResult) -> gamerResult <= Gamer.LIMIT_GAMER_TOTAL_POINT
        && dealerResult == gamerResult),
    LOSE("패", (dealerResult, gamerResult) -> gamerResult > Gamer.LIMIT_GAMER_TOTAL_POINT
        || dealerResult > gamerResult
    );

    private final String result;
    private final BiPredicate<Integer, Integer> predicate;

    GameResult(final String result, final BiPredicate<Integer, Integer> predicate) {
        this.result = result;
        this.predicate = predicate;
    }

    public static GameResult findResult(final int dealerResult, final int gamerResult) {
        return Arrays.stream(values())
            .filter((result) -> result.predicate.test(dealerResult, gamerResult))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하는 결과가 없습니다."));
    }

    public static GameResult convertToDealerResult(final GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public String getResult() {
        return result;
    }
}
