package domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Game {

    private final Dealer dealer;
    private final Players players;

    public Game(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<Player, Result> getResult() {
        return calculate();
    }

    public Map<Result, Integer> getDealerResult() {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);

        for (Result value : getResult().values()) {
            Result reversed = value.reverse();
            Integer orDefault = dealerResult.getOrDefault(reversed, 0);
            dealerResult.put(reversed, orDefault + 1);
        }
        return dealerResult;
    }

    private Map<Player, Result> calculate() {
        final Map<Player, Result> map = new LinkedHashMap<>();
        for (Player player : players.getNames()) { // TODO 인덴트 줄이기
            if (player.isWin(dealer)) {
                map.put(player, Result.WIN);
                continue;
            }
            map.put(player, Result.LOSE);
        }
        return map;
    }
}
