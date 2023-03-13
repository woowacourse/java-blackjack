package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<String, Integer> playerGameResults = new LinkedHashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        calculatePlayersScore(players, dealer);
    }

    private void calculatePlayersScore(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            if (!ResultMatcher.BLACKJACK.equals(player.getResultMatcher())) {
                playerGameResults.put(player.getPlayerName(), calculateProfit(player, dealer));
            }
        }
    }

    private int calculateProfit(Player player, Dealer dealer) {
        double ratio = ResultMatcher.calculateResult(player.getTotalScore(), dealer.getTotalScore()).getRatio();
        return player.getBetAmount().calculateProfit(ratio);
    }

    public int calculateDealerProfit() {
        return -playerGameResults.values().stream()
                .mapToInt(profit -> profit).sum();
    }

    public Map<String, Integer> getPlayerGameResults() {
        return this.playerGameResults;
    }
}
