package blackjack.domain;

import blackjack.domain.card.Score;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private final Map<String, Boolean> playerResults;
    private final int dealerWin;
    private final int dealerLose;

    private Result(Map<String, Boolean> playerResults, int dealerWin, int dealerLose) {
        this.playerResults = playerResults;
        this.dealerWin = dealerWin;
        this.dealerLose = dealerLose;
    }

    public static Result of(Dealer dealer, Players players) {
        Map<String, Boolean> playerResults = CreatePlayersResult(dealer, players);
        int dealerLose = (int) playerResults.values().stream()
                .filter(isWinner -> isWinner)
                .count();
        int dealerWin = players.memberSize() - dealerLose;

        return new Result(playerResults, dealerWin, dealerLose);
    }

    private static Map<String, Boolean> CreatePlayersResult(Dealer dealer, Players players) {
        Map<String, Boolean> playerResults = new LinkedHashMap<>();
        Score dealerScore = dealer.calculateScore();
        for (Player player : players.getPlayers()) {
            playerResults.put(player.getName(), player.isWinner(dealerScore));
        }

        return playerResults;
    }

    public boolean isWinner(String name) {
        return playerResults.get(name);
    }

    public Map<String, Boolean> getPlayerResults() {
        return playerResults;
    }

    public int getDealerWin() {
        return dealerWin;
    }

    public int getDealerLose() {
        return dealerLose;
    }
}
