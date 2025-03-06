package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.HashMap;
import java.util.Map;

public class Victory {
    private final Map<Player, WinningResult> playerVictoryResults;
    private final Map<WinningResult, Integer> dealerVictoryResults;

    public static Victory create(Dealer dealer, Players players) {
        Map<Player, WinningResult> playerVictoryResults = new HashMap<>();
        Map<WinningResult, Integer> dealerVictoryResults = new HashMap<>();
        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        players.sendAll((player -> {
            WinningResult playerWinningResult = winnerDecider.decidePlayerWinning(player);
            playerVictoryResults.put(player, playerWinningResult);

            WinningResult dealerWinningResult = winnerDecider.decideDealerWinning(player);
            dealerVictoryResults.put(
                    dealerWinningResult,
                    dealerVictoryResults.getOrDefault(dealerWinningResult, 0) + 1);
        }));
        return new Victory(playerVictoryResults, dealerVictoryResults);
    }

    public Victory(Map<Player, WinningResult> playerVictoryResults,
                   Map<WinningResult, Integer> dealerVictoryResults) {
        this.playerVictoryResults = playerVictoryResults;
        this.dealerVictoryResults = dealerVictoryResults;
    }

    public Map<Player, WinningResult> getPlayerVictoryResults() {
        return playerVictoryResults;
    }

    public Map<WinningResult, Integer> getDealerVictoryResults() {
        return dealerVictoryResults;
    }
}
