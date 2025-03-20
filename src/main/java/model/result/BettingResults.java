package model.result;

import java.util.Map;

public class BettingResults {

    private final Map<String, Integer> playerBettingResults;

    public BettingResults(final Map<String, Integer> playerBettingResults) {
        this.playerBettingResults = playerBettingResults;
    }

    public int getBettingResultByName(final String name) {
        return playerBettingResults.get(name);
    }

    public int calculateDealerBettingResult() {
        int playersBettingResultSum = playerBettingResults.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return playersBettingResultSum * (-1);
    }
}
