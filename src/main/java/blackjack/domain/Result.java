package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.Map;

public class Result {
    private final Map<Player, Boolean> playerResults;
    private final int dealerWin;
    private final int dealerLose;

    private Result(Dealer dealer, Players players) {
        playerResults = players.createResult(dealer);
        dealerLose = (int) playerResults.values().stream()
                .filter(isWinner -> isWinner)
                .count();
        dealerWin = players.memberSize() - dealerLose;
    }

    public static Result of(Dealer dealer, Players players) {
        return new Result(dealer, players);
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
