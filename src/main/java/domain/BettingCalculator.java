package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingCalculator {

    private final Map<PlayerName, GameResult> gameResults;
    private final Map<PlayerName, BettingMoney> bettingResults;


    public BettingCalculator(Map<PlayerName, GameResult> gameResults, Map<PlayerName, BettingMoney> bettingResults) {
        this.gameResults = gameResults;
        this.bettingResults = bettingResults;
    }

    public Map<PlayerName, Integer> getTotalPlayerProfit() { // 플레이어의 이익을 계산
        Map<PlayerName, Integer> totalPlayerBenefit = new LinkedHashMap<>();
        for (PlayerName playerName : gameResults.keySet()) {
            totalPlayerBenefit.put(playerName, calculatePlayerProfit(playerName));
        }

        return totalPlayerBenefit;
    }

    private int calculatePlayerProfit(PlayerName playerName) {
        GameResult gameResult = gameResults.get(playerName);
        BettingMoney bettingMoney = bettingResults.get(playerName);

        return (int) (gameResult.getBettingRatio() * bettingMoney.bettingMoney());
    }

    public Integer getTotalDealerProfit() {
        return -getTotalPlayerProfit().values().stream()
                .mapToInt(i -> i)
                .sum();
    }
}
