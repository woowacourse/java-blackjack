package domain;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public enum GameResult {
    WIN((playerPoint, dealerPoint) -> playerPoint > dealerPoint),
    LOSE((playerPoint, dealerPoint) -> playerPoint < dealerPoint),
    DRAW(Integer::equals);

    private final BiFunction<Integer, Integer, Boolean> function;

    GameResult(BiFunction<Integer, Integer, Boolean> function) {
        this.function = function;
    }

    public static GameResult getResult(Player player, Dealer dealer) {
        return Arrays.stream(GameResult.values())
                .filter(it -> it.function.apply(getPlayerPoint(player), getPlayerPoint(dealer)))
                .findAny()
                .orElseThrow();
    }

    private static int getPlayerPoint(Player player) {
        if (player.isOverCardPointLimit()) {
            return 0;
        }
        return player.sumCardPool();
    }

    public static Map<GameResult, Integer> makeDealerRecord(Map<Player, GameResult> record) {
        Map<GameResult, Integer> dealerRecord = new HashMap<>();
        int win = calculateGameResult(record.values(), LOSE);
        int draw = calculateGameResult(record.values(), DRAW);
        int lose = calculateGameResult(record.values(), WIN);

        dealerRecord.put(WIN, win);
        dealerRecord.put(DRAW, draw);
        dealerRecord.put(LOSE, lose);

        return dealerRecord;
    }

    private static int calculateGameResult(Collection<GameResult> gameResults, GameResult gameResultType) {
        return (int)gameResults.stream()
                .filter(gameResult -> gameResult == gameResultType)
                .count();

    }
}
