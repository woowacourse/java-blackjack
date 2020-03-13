package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final List<WinOrLose> dealerResults;
    private final Map<Player, WinOrLose> playerResults;

    private GameResult(Map<Player, WinOrLose> playerResults) {
        this.dealerResults = playerResults.values().stream()
                .map(WinOrLose::reverse)
                .collect(Collectors.toList());
        this.playerResults = playerResults;
    }

    public static GameResult of(Dealer dealer, Players players) {
        Map<Player, WinOrLose> userResults = new HashMap<>();

        for (Player player : players.getPlayers()) {
            userResults.put(player, player.isWinner(dealer));
        }

        return new GameResult(userResults);
    }

    public long getDealerWinCount() {
        return dealerResults.stream()
                .filter(dealerResult -> dealerResult == WinOrLose.WIN)
                .count();
    }

    public long getDealerLoseCount() {
        return dealerResults.size() - getDealerWinCount();
    }

    public Map<Player, WinOrLose> getPlayerResults() {
        return playerResults;
    }
}
