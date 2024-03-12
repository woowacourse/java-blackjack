package domain.game;

import domain.constant.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;

import java.util.*;

import static domain.constant.GameResult.*;
import static java.util.Collections.*;

public record GameResults(List<GameResult> dealerGameResults, Map<PlayerName, GameResult> playerGameResults) {

    public static GameResults of(Dealer dealer, List<Player> players) {
        List<GameResult> dealerGameResults = new ArrayList<>();
        Map<PlayerName, GameResult> playerGameResults = new HashMap<>();
        players.forEach(player -> determineGameResult(dealer, player, dealerGameResults, playerGameResults));

        return new GameResults(unmodifiableList(dealerGameResults), unmodifiableMap(playerGameResults));
    }

    private static void determineGameResult(
            final Dealer dealer,
            final Player player,
            final List<GameResult> dealerGameResults,
            final Map<PlayerName, GameResult> playerGameResults
    ) {
        if (dealer.isWin(player)) {
            addGameResult(dealerGameResults, playerGameResults, player, WIN, LOSE);
            return;
        }
        if (player.isWin(dealer)) {
            addGameResult(dealerGameResults, playerGameResults, player, LOSE, WIN);
            return;
        }
        addGameResult(dealerGameResults, playerGameResults, player, DRAW, DRAW);
    }

    private static void addGameResult(
            final List<GameResult> dealerGameResults,
            final Map<PlayerName, GameResult> playerGameResults,
            Player player,
            GameResult dealerGameResult,
            GameResult playerGameResult
    ) {
        dealerGameResults.add(dealerGameResult);
        playerGameResults.put(player.getPlayerName(), playerGameResult);
    }
}
