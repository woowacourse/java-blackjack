package domain.game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.*;
import java.util.function.BiFunction;

public enum GameResult {

    WIN((playerPoint, dealerPoint) -> playerPoint > dealerPoint),
    LOSE((playerPoint, dealerPoint) -> playerPoint < dealerPoint),
    DRAW(Integer::equals);

    private static final int OUT_OF_GAME_POINT = 0;

    private final BiFunction<Integer, Integer, Boolean> criteria;

    GameResult(BiFunction<Integer, Integer, Boolean> criteria) {
        this.criteria = criteria;
    }

    public static GameResult getResult(Player player, Dealer dealer) {
        return Arrays.stream(GameResult.values())
                .filter(it -> it.criteria.apply(getPlayerPoint(player), getPlayerPoint(dealer)))
                .findAny()
                .orElseThrow();
    }

    private static int getPlayerPoint(Player player) {
        if (player.isBurst()) {
            return OUT_OF_GAME_POINT;
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
        return (int) gameResults.stream()
                .filter(gameResult -> gameResult == gameResultType)
                .count();

    }
}
