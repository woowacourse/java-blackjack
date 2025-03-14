package blackjack.domain;

import blackjack.domain.card.BlackjackScore;
import blackjack.domain.card.WinningResult;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, WinningResult> playerGameResults;
    private final Map<WinningResult, Integer> dealerGameResults;

    public GameResult(Map<Player, WinningResult> playerGameResults,
                      Map<WinningResult, Integer> dealerGameResults) {
        this.playerGameResults = playerGameResults;
        this.dealerGameResults = dealerGameResults;
    }

    public static GameResult create(Dealer dealer, Players players) {
        Map<Player, WinningResult> playerGameResults = createPlayerGameResult(dealer, players);
        Map<WinningResult, Integer> dealerGameResults = createDealerGameResults(dealer, players);
        return new GameResult(playerGameResults, dealerGameResults);
    }

    private static Map<Player, WinningResult> createPlayerGameResult(Dealer dealer, Players players) {
        Map<Player, WinningResult> playerGameResults = new HashMap<>();
        players.sendAll((player -> {
            BlackjackScore playerScore = player.getCards().calculateScore();
            BlackjackScore dealerScore = dealer.getCards().calculateScore();
            WinningResult playerWinningResult = playerScore.decide(dealerScore);
            playerGameResults.put(player, playerWinningResult);
        }));
        return playerGameResults;
    }

    private static Map<WinningResult, Integer> createDealerGameResults(Dealer dealer, Players players) {
        Map<WinningResult, Integer> dealerGameResults = new HashMap<>();
        players.sendAll((player -> {
            BlackjackScore dealerScore = dealer.getCards().calculateScore();
            BlackjackScore playerScore = player.getCards().calculateScore();
            WinningResult dealerWinningResult = dealerScore.decide(playerScore);
            dealerGameResults.put(
                    dealerWinningResult,
                    dealerGameResults.getOrDefault(dealerWinningResult, 0) + 1);
        }));
        return dealerGameResults;
    }

    public static int getMultiplyRatio(BlackjackScore playerScore, BlackjackScore dealerScore) {
        WinningResult winningResult = playerScore.decide(dealerScore);
        if (winningResult.equals(WinningResult.WIN)) {
            return 1;
        }
        if (winningResult.equals(WinningResult.LOSE)) {
            return -1;
        }
        return 0;
    }

    public Map<Player, WinningResult> getPlayerGameResults() {
        return playerGameResults;
    }

    public Map<WinningResult, Integer> getDealerGameResults() {
        return dealerGameResults;
    }
}
