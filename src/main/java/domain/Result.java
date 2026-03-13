package domain;

import java.util.Map;

public class Result {
    private final Dealer dealer;
    private final Map<String, Boolean> playerResults;

    public Result(Dealer dealer, Map<String, Boolean> playerResults) {
        this.dealer = dealer;
        this.playerResults = playerResults;
    }

    public int getDealerWin() {
        return (int) playerResults.values().stream().filter(v -> !v).count();
    }

    public int getDealerLose() {
        return (int) playerResults.values().stream().filter(v -> v).count();
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Map<String, Boolean> getPlayerResults() {
        return Map.copyOf(playerResults);
    }
}
