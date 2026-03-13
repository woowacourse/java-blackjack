package domain;

import java.util.Map;

public class Result {
    private final Dealer dealer;
    private final Map<String, MatchResult> playerResults;

    public Result(Dealer dealer, Map<String, MatchResult> playerResults) {
        this.dealer = dealer;
        this.playerResults = playerResults;
    }

    public int getDealerWin() {
        return countResult(MatchResult.LOSE);
    }

    public int getDealerLose() {
        return countResult(MatchResult.WIN);
    }

    public int getDealerDraw() {
        return countResult(MatchResult.DRAW);
    }

    private int countResult(MatchResult matchResult) {
        return (int) playerResults.values().stream()
                .filter(result -> result.equals(matchResult))
                .count();
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Map<String, MatchResult> getPlayerResults() {
        return Map.copyOf(playerResults);
    }
}
