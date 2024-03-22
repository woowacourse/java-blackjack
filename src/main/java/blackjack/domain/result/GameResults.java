package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResults {
    private final Map<Player, GameResult> gameResults;

    public GameResults() {
        this(new HashMap<>());
    }

    public GameResults(final Map<Player, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public GameResults(final Players players, final Dealer dealer) {
        Map<Player, GameResult> gameResults = new HashMap<>();
        for (final Player player : players.getPlayers()) {
            gameResults.put(player, GameResult.ofPlayer(player, dealer));
        }

        this.gameResults = gameResults;
    }

    public GamerProfits calculateGamerProfits() {
        Map<Player, Integer> playerProfits = new HashMap<>();
        int dealerProfit = 0;

        for (Map.Entry<Player, GameResult> entry : gameResults.entrySet()) {
            int playerProfit = entry.getKey().calculateProfit(entry.getValue());
            playerProfits.put(entry.getKey(), playerProfit);
            dealerProfit -= playerProfit;
        }

        return new GamerProfits(playerProfits, dealerProfit);
    }

    @Override
    public String toString() {
        return "GameResults{" +
                "gameResults=" + gameResults +
                '}';
    }
}
