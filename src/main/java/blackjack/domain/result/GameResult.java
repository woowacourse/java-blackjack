package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final List<WinOrLose> dealerResults;
    private final Map<User, WinOrLose> playerResults;

    private GameResult(Map<User, WinOrLose> playerResults) {
        this.dealerResults = playerResults.values().stream()
                .map(WinOrLose::reverse)
                .collect(Collectors.toList());
        this.playerResults = playerResults;
    }

    public static GameResult of(Dealer dealer, Players players) {
        Map<User, WinOrLose> userResults = new HashMap<>();

        for (User player : players.getPlayers()) {
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

    public Map<User, WinOrLose> getPlayerResults() {
        return playerResults;
    }

}
