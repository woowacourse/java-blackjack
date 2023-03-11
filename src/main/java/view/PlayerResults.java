package view;

import domain.GameResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlayerResults implements Iterable<PlayerResult> {

    private final List<PlayerResult> playerResults;

    public PlayerResults(List<PlayerResult> playerResults) {
        this.playerResults = playerResults;
    }

    public DealerResult getDealerResult() {
        final Map<GameResult, Integer> result = new HashMap<>();
        for (PlayerResult playerResult : playerResults) {
            GameResult reverseResult = playerResult.getGameResult().reverse();
            result.put(reverseResult, result.getOrDefault(reverseResult, 0) + 1);
        }
        return new DealerResult(result);
    }

    @Override
    public Iterator<PlayerResult> iterator() {
        return playerResults.iterator();
    }
}
