package domain.match;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, MatchResult> results;

    public GameResult(Players players, Dealer dealer) {
        this.results = new HashMap<>();

        for (Player player : players.getPlayers()) {
            results.put(player, MatchResult.judge(player, dealer));
        }
    }

    public Map<Player, Integer> calculatePlayersProfit() {
        Map<Player, Integer> profit = new HashMap<>();

        for (Map.Entry<Player, MatchResult> entry : results.entrySet()) {

            Player player = entry.getKey();
            MatchResult result = entry.getValue();

            profit.put(player, player.applyMatchResultToBet(result));
        }

        return profit;
    }

    public int calculateDealerProfit() {
        int dealerProfit = 0;

        for (int playerProfit : calculatePlayersProfit().values()) {
            dealerProfit -= playerProfit;
        }

        return dealerProfit;
    }
}
