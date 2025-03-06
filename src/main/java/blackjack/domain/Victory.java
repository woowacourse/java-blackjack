package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class Victory {
    private final Map<Player, Map<WinningResult, Integer>> playerVictorytResults;
    private final Map<WinningResult, Integer> dealerVictoryResults;

    public static Victory create(Dealer dealer, Players players) {
        Map<Player, Map<WinningResult, Integer>> playerVictorytResults = new HashMap<>();
        Map<WinningResult, Integer> dealerVictoryResults = new HashMap<>();
        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        players.sendAll((player -> {
            WinningResult playerWinningResult = winnerDecider.decidePlayerWinning(player);
            Map<WinningResult, Integer> playerWinningResults = playerVictorytResults.getOrDefault(
                    player,
                    new HashMap<>());
            playerWinningResults.put(playerWinningResult,
                    playerWinningResults.getOrDefault(playerWinningResult, 0) + 1);
            playerVictorytResults.put(player, playerWinningResults);

            WinningResult dealerWinningResult = winnerDecider.decideDealerWinning(player);
            dealerVictoryResults.put(
                    dealerWinningResult,
                    dealerVictoryResults.getOrDefault(dealerWinningResult, 0) + 1);
        }));
        return new Victory(playerVictorytResults, dealerVictoryResults);
    }

    public Victory(Map<Player, Map<WinningResult, Integer>> playerVictorytResults,
                   Map<WinningResult, Integer> dealerVictoryResults) {
        this.playerVictorytResults = playerVictorytResults;
        this.dealerVictoryResults = dealerVictoryResults;
    }

    public Map<Player, Map<WinningResult, Integer>> getPlayerVictorytResults() {
        return playerVictorytResults;
    }

    public Map<WinningResult, Integer> getDealerVictoryResults() {
        return dealerVictoryResults;
    }
}
