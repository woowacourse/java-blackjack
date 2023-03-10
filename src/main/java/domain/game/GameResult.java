package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<Result, List<Player>> playerResult = new EnumMap<>(Result.class);

    private GameResult(final Dealer dealer, final Players players) {
        final GamePoint dealerPoint = dealer.calculatePoint();
        updateGameResult(Result.WIN, players.findPlayerGreaterThan(dealerPoint));
        updateGameResult(Result.DRAW, players.findPlayerSameAs(dealerPoint));
        updateGameResult(Result.LOSE, players.findPlayersLowerThan(dealerPoint));
    }

    public static GameResult of(final Dealer dealer, final Players players) {
        return new GameResult(dealer, players);
    }

    private void updateGameResult(final Result result, final List<Player> players) {
        if (players.size() > 0) {
            playerResult.put(result, Collections.unmodifiableList(players));
        }
    }

    public Map<Result, List<Player>> getResult() {
        return playerResult;
    }
}
