package domain.game;

import domain.constant.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;

import java.util.*;

import static domain.constant.GameResult.*;
import static java.util.Collections.*;

public record BlackjackGameResults(List<GameResult> dealerGameResults, Map<PlayerName, GameResult> playerGameResults) {

    public static BlackjackGameResults of(final BlackjackGame blackjackGame) {
        return BlackjackGameResults.of(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    public static BlackjackGameResults of(final Dealer dealer, final List<Player> players) {
        List<GameResult> dealerGameResults = new ArrayList<>();
        Map<PlayerName, GameResult> playerGameResults = new HashMap<>();
        players.forEach(player -> determineGameResult(dealer, player, dealerGameResults, playerGameResults));

        return new BlackjackGameResults(unmodifiableList(dealerGameResults), unmodifiableMap(playerGameResults));
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
            final Player player,
            final GameResult dealerGameResult,
            final GameResult playerGameResult
    ) {
        dealerGameResults.add(dealerGameResult);
        playerGameResults.put(player.getPlayerName(), playerGameResult);
    }
}
