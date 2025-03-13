package blackjack.domain.card;

import static blackjack.domain.GameResult.getMultiplyRatio;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.HashMap;
import java.util.Map;

public class BettingResult {
    private final Map<Player, Integer> playerBettingResults;
    private static int dealerBettingResult;

    public BettingResult(Map<Player, Integer> playerBettingResults, int dealerBettingResult) {
        this.playerBettingResults = playerBettingResults;
    }

    public static BettingResult create(Dealer dealer, Players players) {
        Map<Player, Integer> playerBettingResults = new HashMap<>();
        players.sendAll((player -> {
            BlackjackScore playerScore = player.getCards().calculateScore();
            BlackjackScore dealerScore = dealer.getCards().calculateScore();
            int playerBettingResult = getMultiplyRatio(playerScore, dealerScore);
            int profit = player.makeProfit(playerBettingResult);
            playerBettingResults.put(player, profit);
            dealerBettingResult -= profit;
        }));

        return new BettingResult(playerBettingResults, dealerBettingResult);
    }

    public Map<Player, Integer> getPlayerBettingResults() {
        return playerBettingResults;
    }

    public int getDealerBettingResult() {
        return dealerBettingResult;
    }
}
