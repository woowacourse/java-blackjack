package blackjack;

import blackjack.common.GameResultType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, GameResultType> results;

    private PlayersResult() {
        this.results = new LinkedHashMap<>();
    }

    public static PlayersResult create() {
        return new PlayersResult();
    }

    public void save(Player player, GameResultType gameResultType) {
        results.put(player, gameResultType);
    }

    public Map<Player, GameResultType> getAllResult() {
        return Collections.unmodifiableMap(results);
    }
}
