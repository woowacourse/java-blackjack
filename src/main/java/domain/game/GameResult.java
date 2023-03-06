package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
    private final Map<Result, List<Player>> playerResult = new EnumMap<>(Result.class);

    private GameResult(final Dealer dealer, final Players players) {
        final GamePoint dealerPoint = dealer.calculatePoint();
        updateGameResult(Result.WIN, players.findPlayerGreaterThan(dealerPoint));
        updateGameResult(Result.DRAW, players.findPlayerSameAs(dealerPoint));
        updateGameResult(Result.LOSE, players.findPlayersLowerThan(dealerPoint));
    }

    public static GameResult create(final Dealer dealer, final Players players) {
        return new GameResult(dealer, players);
    }

    private void updateGameResult(final Result result, final List<Player> players) {
        if (players.size() > 0) {
            playerResult.put(result, Collections.unmodifiableList(players));
            dealerResult.put(reverse(result), dealerResult.getOrDefault(reverse(result), 0) + players.size());
        }
    }

    private Result reverse(Result value) {
        if (value == Result.WIN) {
            return Result.LOSE;
        }
        if (value == Result.DRAW) {
            return Result.DRAW;
        }
        if (value == Result.LOSE) {
            return Result.WIN;
        }
        throw new AssertionError();
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Result, List<Player>> getPlayerResult() {
        return playerResult;
    }
}
