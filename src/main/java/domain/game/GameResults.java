package domain.game;

import domain.constant.GameResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;

import java.util.*;

import static domain.constant.GameResult.*;
import static java.util.Collections.*;

public class GameResults {
    private final List<GameResult> dealerGameResults;
    private final Map<PlayerName, GameResult> playerGameResults;

    public GameResults(final List<GameResult> dealerGameResults, final Map<PlayerName, GameResult> playerGameResults) {
        this.dealerGameResults = dealerGameResults;
        this.playerGameResults = playerGameResults;
    }

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
        if (isDealerWin(dealer, player)) {
            addGameResult(dealerGameResults, playerGameResults, player, WIN, LOSE);
            return;
        }
        if (isPlayerWin(dealer, player)) {
            addGameResult(dealerGameResults, playerGameResults, player, LOSE, WIN);
            return;
        }
        addGameResult(dealerGameResults, playerGameResults, player, DRAW, DRAW);
    }

    private static boolean isDealerWin(Dealer dealer, Player player) {
        return player.isBust()
                || (dealer.isBlackJack() && !player.isBlackJack())
                || (!dealer.isBust() && dealer.getTotalScore().isBigger(player.getTotalScore().value()));
    }

    private static boolean isPlayerWin(Dealer dealer, Player player) {
        return (dealer.isBust() && !player.isBust())
                || (player.isBlackJack() && !dealer.isBlackJack())
                || player.getTotalScore().isBigger(dealer.getTotalScore().value());
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

    public List<GameResult> getDealerGameResults() {
        return unmodifiableList(dealerGameResults);
    }

    public Map<PlayerName, GameResult> getPlayerGameResults() {
        return unmodifiableMap(playerGameResults);
    }
}
