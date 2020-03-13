package blackjack.domain;

import blackjack.domain.card.Score;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private final Map<Player, Boolean> playerResult;
    private final int dealerWin;
    private final int dealerLose;

    private Result(Map<Player, Boolean> playerResult, int dealerWin, int dealerLose) {
        this.playerResult = playerResult;
        this.dealerWin = dealerWin;
        this.dealerLose = dealerLose;
    }

    public static Result of(Dealer dealer, Players players) {
        Map<Player, Boolean> playerResults = CreatePlayersResult(dealer, players);
        int dealerWin = calculateDealerWin(playerResults);
        int dealerLose = calculateDealerLose(playerResults);

        return new Result(playerResults, dealerWin, dealerLose);
    }

    private static Map<Player, Boolean> CreatePlayersResult(Dealer dealer, Players players) {
        Map<Player, Boolean> playerResults = new LinkedHashMap<>();
        Score dealerScore = dealer.calculateScore();
        for (Player player : players.getPlayers()) {
            playerResults.put(player, player.isWinner(dealerScore));
        }

        return playerResults;
    }

    private static int calculateDealerWin(Map<Player, Boolean> playerResults) {
        return (int) playerResults.values().stream()
                .filter(isWinner -> !isWinner)
                .count();
    }

    private static int calculateDealerLose(Map<Player, Boolean> playerResults) {
        return (int) playerResults.values().stream()
                .filter(isWinner -> isWinner)
                .count();
    }

    public boolean isWinner(Player player) {
        return playerResult.get(player);
    }

    public Map<Player, Boolean> getPlayerResult() {
        return playerResult;
    }

    public int getDealerWin() {
        return dealerWin;
    }

    public int getDealerLose() {
        return dealerLose;
    }
}
