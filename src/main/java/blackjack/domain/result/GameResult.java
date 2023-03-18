package blackjack.domain.result;

import blackjack.domain.card.Card;
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
            playerGameResults.put(player.getUserName(), calculateProfit(player, dealer.getHand()));
        }
    }

    private int calculateProfit(Player player, List<Card> dealerCards) {
        double ratio = RatioMather.of(player.getHand(), dealerCards).getRatio();
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
