package domain.game;

import domain.player.Dealer;
import domain.player.Player;

import java.util.*;

public class GameResult {

    private final Map<Player, MatchResult> gameResult = new LinkedHashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        initialGameResult(new ArrayList<>(players), dealer);
    }

    private void initialGameResult(List<Player> players, Dealer dealer) {
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

    @Override
    public String toString() {
        return "GameResult{" +
                "gameResult=" + gameResult +
                '}';
    }
}
