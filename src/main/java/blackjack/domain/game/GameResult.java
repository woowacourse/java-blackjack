package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, MatchResult> gameResult = new LinkedHashMap<>();

    public GameResult(Players players, Dealer dealer) {
        initialGameResult(players, dealer);
    }

    private void initialGameResult(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
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
