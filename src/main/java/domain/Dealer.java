package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    public static final int DRAW_BOUNDARY = 16;

    private final Map<GameResult, Integer> result;

    private Dealer(Cards cards) {
        super(cards);
        this.result = new EnumMap<>(GameResult.class);
    }

    public static Dealer init() {
        return new Dealer(Cards.empty());
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }

    public boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }

    public Map<Player, GameResult> getGameResult(List<Player> players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players) {
            GameResult judge = GameResult.judge(player, this);
            gameResult.put(player, judge);
            GameResult dealerResult = judge.getReverse();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return gameResult;
    }

    public Map<GameResult, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
