package domain;

import domain.constant.Result;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.*;

public class GameResult {

    private final Map<Player, Profit> playerResults;
    private final int dealerProfit;

    public GameResult(Dealer dealer, List<Player> players) {
        this.playerResults = aggregatePlayerResults(dealer, players);
        this.dealerProfit = calculateDealerResult();
    }

    public Map<Player, Profit> getPlayerResults() {
        return playerResults;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    private Map<Player, Profit> aggregatePlayerResults(Dealer dealer, List<Player> players) {
        Map<Player, Profit> playerResults = new LinkedHashMap<>();
        for (Player player : players) {
            playerResults.put(
                    player,
                    new Profit(player.getBetAmount(), Result.of(dealer, player)
                    ));
        }
        return playerResults;
    }

    private int calculateDealerResult() {
        int playerProfitSum = playerResults.values().stream()
                .mapToInt(Profit::getAmount)
                .sum();

        return playerProfitSum * -1;
    }
}
