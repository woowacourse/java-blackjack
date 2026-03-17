package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.*;

public class GameResult {

    private final Map<Player, Profit> playerResults;
    private final int dealerProfit;

    private GameResult(Map<Player, Profit> playerResults, int dealerProfit) {
        this.playerResults = playerResults;
        this.dealerProfit = dealerProfit;
    }

    public static GameResult of(Dealer dealer, Players players) {
        Map<Player, Profit> playerResults = aggregatePlayerResults(dealer, players.getAll());
        return new GameResult(
                playerResults,
                calculateDealerResult(playerResults)
        );
    }

    public Map<Player, Profit> getPlayerResults() {
        return playerResults;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    private static Map<Player, Profit> aggregatePlayerResults(Dealer dealer, List<Player> players) {
        Map<Player, Profit> playerResults = new LinkedHashMap<>();
        for (Player player : players) {
            playerResults.put(
                    player,
                    new Profit(player.getBetAmount(), Result.of(dealer, player)
                    ));
        }
        return playerResults;
    }

    private static int calculateDealerResult(Map<Player, Profit> playerResults) {
        int playerProfitSum = playerResults.values().stream()
                .mapToInt(Profit::getAmount)
                .sum();

        return playerProfitSum * -1;
    }
}
