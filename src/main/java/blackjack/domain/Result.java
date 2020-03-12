package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.Map;

public class Result {
    private final Map<Player, Boolean> playerResults;
    private final int dealerWin;
    private final int dealerLose;

    private Result(Map<Player, Boolean> playerResults, int dealerWin, int dealerLose) {
        this.playerResults = playerResults;
        this.dealerWin = dealerWin;
        this.dealerLose = dealerLose;
    }

    public static Result of(Dealer dealer, Players players) {
        Map<Player, Boolean> playerResults = players.createResult(dealer);
        int dealerLose = (int) playerResults.values().stream()
                .filter(isWinner -> isWinner)
                .count();
        int dealerWin = players.memberSize() - dealerLose;
        
        return new Result(playerResults, dealerWin, dealerLose);
    }

    public boolean isWinner(Player player) {
        return playerResults.get(player);
    }

    public Map<Player, Boolean> getPlayerResults() {
        return playerResults;
    }

    public int getDealerWin() {
        return dealerWin;
    }

    public int getDealerLose() {
        return dealerLose;
    }
}
