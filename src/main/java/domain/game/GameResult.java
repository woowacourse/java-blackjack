package domain.game;

import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, MatchResult> gameResult = new LinkedHashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            if (playerWinCondition(player, dealer)) {
                gameResult.put(player, MatchResult.WIN);
                continue;
            }
            gameResult.put(player, MatchResult.LOSE);
        }
    }

    private boolean playerWinCondition(Player player, Dealer dealer) {
        return !player.getCards().isBust()
                && (dealer.getCards().isBust() || dealer.getCards().sum() < player.getCards().sum());
    }

    public MatchResult getMatchResult(Player player) {
        return gameResult.get(player);
    }

    public long getDealerWinCount() {
        return gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == MatchResult.LOSE)
                .count();
    }

    public long getDealerLoseCount() {
        return gameResult.size() - getDealerWinCount();
    }

    public Map<Player, MatchResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }
}
