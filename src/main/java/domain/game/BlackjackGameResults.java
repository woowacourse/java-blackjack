package domain.game;

import domain.constant.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

public record BlackjackGameResults(List<GameResult> dealerGameResults, Map<PlayerName, GameResult> playerGameResults) {

    public static BlackjackGameResults of(final BlackjackGame blackjackGame) {
        return BlackjackGameResults.of(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    public static BlackjackGameResults of(final Dealer dealer, final List<Player> players) {
        List<GameResult> dealerGameResults = new ArrayList<>();
        Map<PlayerName, GameResult> playerGameResults = new HashMap<>();
        players.forEach(player -> addGameResult(dealerGameResults, playerGameResults, player, dealer));

        return new BlackjackGameResults(unmodifiableList(dealerGameResults), unmodifiableMap(playerGameResults));
    }

    private static void addGameResult(
            final List<GameResult> dealerGameResults,
            final Map<PlayerName, GameResult> playerGameResults,
            final Player player,
            final Dealer dealer
    ) {
        GameResult playerGameResult = player.determineGameResult(dealer);
        dealerGameResults.add(playerGameResult.getReverseResult());
        playerGameResults.put(player.getPlayerName(), playerGameResult);
    }
}
