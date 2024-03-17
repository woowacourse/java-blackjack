package blackjack.domain.result;

import blackjack.domain.gamer.Gamer;

import java.util.HashMap;
import java.util.Map;

public class GamerResults {
    private final Map<Gamer, GameResult> gamerResults;

    public GamerResults() {
        this(new HashMap<>());
    }

    public GamerResults(Map<Gamer, GameResult> gamerResults) {
        this.gamerResults = gamerResults;
    }

    public void add(final Gamer gamer, final GameResult gameResult) {
        gamerResults.put(gamer, gameResult);
    }
}
