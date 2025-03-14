package model.bet;

import java.util.Map;

public class BettingResults {

    private final Map<String, Integer> playerBettingResults;

    public BettingResults(final Map<String, Integer> playerBettingResults) {
        this.playerBettingResults = playerBettingResults;
    }

    public int getBettingResultByName(final String name) {
        return playerBettingResults.get(name);
    }
}
