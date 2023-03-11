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

    GameResult(final BiFunction<Integer, Integer, Boolean> criteria) {
        this.criteria = criteria;
    }

    public static GameResult makePlayerRecord(final Player player, final Dealer dealer) {
        return Arrays.stream(values())
                .filter(it -> it.criteria.apply(getPlayerPoint(player), getPlayerPoint(dealer)))
                .findAny()
                .orElseThrow();
    }

    private static int getPlayerPoint(final Player player) {
        if (player.isBurst()) {
            return OUT_OF_GAME_POINT;
        }
        return player.sumCardPool();
    }

    public static Map<GameResult, Integer> makeDealerRecord(final Map<Player, GameResult> record) {
        Map<GameResult, Integer> dealerRecord = new HashMap<>();

        dealerRecord.put(LOSE, countGameResult(record.values(), WIN));
        dealerRecord.put(WIN, countGameResult(record.values(), LOSE));
        dealerRecord.put(DRAW, countGameResult(record.values(), DRAW));

        return dealerRecord;
    }

    private static int countGameResult(final Collection<GameResult> gameResults, final GameResult gameResultType) {
        return Collections.frequency(gameResults, gameResultType);
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }
}
