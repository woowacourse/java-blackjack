package domain.game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum GameResult {
    WIN((playerPoint, dealerPoint) -> playerPoint > dealerPoint),
    LOSE((playerPoint, dealerPoint) -> playerPoint < dealerPoint),
    DRAW(Integer::equals);

    private final BiFunction<Integer, Integer, Boolean> comparePoint;

    GameResult(BiFunction<Integer, Integer, Boolean> comparePoint) {
        this.comparePoint = comparePoint;
    }

    public static GameResult getResult(Player player, Dealer dealer) {
        return Arrays.stream(GameResult.values())
                .filter(it -> it.comparePoint.apply(
                                getPlayerPoint(player),
                                getPlayerPoint(dealer)
                        )
                )
                .findAny()
                .orElseThrow();
    }

    private static int getPlayerPoint(Player player) {
        if (player.isBust()) {
            return 0;
        }
        return player.sumHand();
    }

    public static Map<GameResult, Integer> makeDealerRecord(Map<Player, GameResult> record) {
        Map<GameResult, Integer> dealerRecord = new HashMap<>();
        int win = countMatchedResults(record.values(), LOSE);
        int draw = countMatchedResults(record.values(), DRAW);
        int lose = countMatchedResults(record.values(), WIN);

        dealerRecord.put(WIN, win);
        dealerRecord.put(DRAW, draw);
        dealerRecord.put(LOSE, lose);

        return dealerRecord;
    }

    private static int countMatchedResults(Collection<GameResult> gameResults, GameResult gameResultType) {
        return (int) gameResults.stream()
                .filter(gameResult -> gameResult == gameResultType)
                .count();
    }
}
