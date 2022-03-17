package blackjack.domain;

import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

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

    public static Map<Gamer, GameResult> calculateGamersFinalResultBoard(final Player dealer,
        final List<Gamer> gamers) {
        int dealerResult = dealer.calculateResult();
        return gamers.stream()
            .collect(Collectors.toMap(gamer -> gamer,
                gamer -> GameResult.findResult(dealerResult, gamer.calculateResult()),
                (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static Map<GameResult, Integer> calculateDealerFinalResultBoard(
        final Map<Gamer, GameResult> gamerResultBoard) {
        Map<GameResult, Integer> enumMap = new EnumMap<>(GameResult.class);
        for (GameResult gameResult : gamerResultBoard.values()) {
            GameResult dealerGameResult = GameResult.convertToDealerResult(gameResult);
            enumMap.put(dealerGameResult, enumMap.getOrDefault(dealerGameResult, 0) + 1);
        }
        return enumMap;
    }

    public String getResult() {
        return result;
    }
}
