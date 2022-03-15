package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import java.util.HashMap;
import java.util.Map;

public final class Statistic {
    private final Map<Player, Result> playersResult = new HashMap<>();
    private final Map<Result, Integer> dealerResults = new HashMap<>();
    
    private Statistic(BlackjackTable blackjackTable) {
        for (Result value : Result.values()) {
            dealerResults.put(value, 0);
        }
        calculate(blackjackTable);
    }
    
    public static Statistic from(BlackjackTable blackjackTable) {
        return new Statistic(blackjackTable);
    }
    
    public Map<Result, Integer> getDealerResults() {
        return dealerResults;
    }
    
    public Map<Player, Result> getPlayersResult() {
        return playersResult;
    }
    
    private void calculate(BlackjackTable table) {
        for (Player player : table.getPlayers().get()) {
            Result playerResult = calculatePlayerResult(player, table.getDealer());
            Result dealerResult = playerResult.toReverse();
            playersResult.put(player, playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
    }
    
    private Result calculatePlayerResult(final Player player, final Dealer dealer) {
        if (dealer.isBurst()) {
            return Result.fromIsWin(!player.isBurst());
        }
        if (!player.isBurst() && player.isDraw(dealer)) {
            return Result.DRAW;
        }
        return Result.fromIsWin(!player.isBurst() && player.isWinner(dealer));
    }
}
