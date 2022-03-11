package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, MatchResult> gameResult;

    public GameResult(Players players, Dealer dealer) {
        gameResult = new LinkedHashMap<>(players.match(dealer));
    }

    public MatchResult getMatchResult(Player player) {
        return gameResult.get(player);
    }

    public long getDealerDrawCount() {
        return gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == MatchResult.DRAW)
                .count();
    }

    public long getDealerWinCount() {
        return gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == MatchResult.LOSE)
                .count();
    }

    public long getDealerLoseCount() {
        return gameResult.size() - getDealerDrawCount() - getDealerWinCount();
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
