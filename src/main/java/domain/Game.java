package domain;

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
