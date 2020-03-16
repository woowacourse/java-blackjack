package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final List<ResultType> dealerResults;
    private final Map<Player, ResultType> playerResults;

    private GameResult(Map<Player, ResultType> playerResults) {
        this.dealerResults = playerResults.values().stream()
                .map(ResultType::reverse)
                .collect(Collectors.toList());
        this.playerResults = playerResults;
    }

    public static GameResult of(Dealer dealer, Players players) {
        Map<Player, ResultType> userResults = new HashMap<>();

        for (Player player : players.getPlayers()) {
            userResults.put(player, PlayerResult.of(player, dealer));
        }

        return new GameResult(userResults);
    }

    public long getDealerWinCount() {
        return dealerResults.stream()
                .filter(ResultType::isWin)
                .count();
    }

    public long getDealerDrawCount() {
        return dealerResults.stream()
                .filter(ResultType::isDraw)
                .count();
    }

    public long getDealerLoseCount() {
        return dealerResults.size() - getDealerWinCount();
    }

    public Map<Player, ResultType> getPlayerResults() {
        return playerResults;
    }
}
