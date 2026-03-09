package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Judgement {
    public Map<String, GameResult> calculatePlayerResults(Players players, Dealer dealer) {
        Map<String, GameResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult gameResult = player.compareScore(dealer.calculateTotalScore());
            playerResults.put(player.getName(), gameResult);
        }
        return playerResults;
    }

    public Map<GameResult, Integer> calculateDealerResults(Map<String, GameResult> playerResults) {
        Map<GameResult, Integer> dealerResults = new LinkedHashMap<>();
        for (GameResult result : playerResults.values()) {
            GameResult dealerResult = result.toDealerResult();
            dealerResults.put(dealerResult, dealerResults.getOrDefault(dealerResult, 0) + 1);
        }
        return dealerResults;
    }
}
