package blackjack.domain;

import blackjack.domain.card.WinningResult;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, WinningResult> playerVictoryResults;
    private final Map<WinningResult, Integer> dealerVictoryResults;

    public GameResult(Map<Player, WinningResult> playerVictoryResults,
                      Map<WinningResult, Integer> dealerVictoryResults) {
        this.playerVictoryResults = playerVictoryResults;
        this.dealerVictoryResults = dealerVictoryResults;
    }

    public static GameResult create(Dealer dealer, Players players) {
        Map<Player, WinningResult> playerVictoryResults = new HashMap<>();
        Map<WinningResult, Integer> dealerVictoryResults = new HashMap<>();

        players.sendAll((player -> {
            WinningResult playerWinningResult = WinningResult.decide(player.getCards(), dealer.getCards());
            playerVictoryResults.put(player, playerWinningResult);

            WinningResult dealerWinningResult = WinningResult.decide(dealer.getCards(), player.getCards());
            dealerVictoryResults.put(
                    dealerWinningResult,
                    dealerVictoryResults.getOrDefault(dealerWinningResult, 0) + 1);
        }));
        return new GameResult(playerVictoryResults, dealerVictoryResults);
    }

    public Map<Player, WinningResult> getPlayerVictoryResults() {
        return playerVictoryResults;
    }

    public Map<WinningResult, Integer> getDealerVictoryResults() {
        return dealerVictoryResults;
    }
}
