package blackjack.domain;

import blackjack.domain.participants.Name;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final List<Integer> dealerCounts;
    private final Map<Name, Result> playerResults;

    public GameResult(final List<Integer> dealerCounts, final Map<Name, Result> playerResults) {
        this.dealerCounts = new ArrayList<>(dealerCounts);
        this.playerResults = new LinkedHashMap<>(playerResults);
    }

    public List<Integer> getDealerCounts() {
        return dealerCounts;
    }

    public Map<Name, Result> getPlayerResults() {
        return playerResults;
    }
}
