package blackjack.domain.result;

import blackjack.domain.BlackjackRepository;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import java.util.HashMap;
import java.util.Map;

public final class ResultStatistic {
    private final Map<String, Result> playersResult = new HashMap<>();
    private final Map<Result, Integer> dealerResults = new HashMap<>();
    
    private ResultStatistic(BlackjackRepository blackjackRepository) {
        for (Result value : Result.values()) {
            dealerResults.put(value, 0);
        }
        calculate(blackjackRepository);
    }
    
    public static ResultStatistic from(BlackjackRepository blackjackRepository) {
        return new ResultStatistic(blackjackRepository);
    }
    
    public Map<Result, Integer> getDealerResults() {
        return (dealerResults);
    }
    
    public Map<String, Result> getPlayersResult() {
        HashMap<String, Result> result = new HashMap<>();
        for (String name : playersResult.keySet()) {
            result.put(name, playersResult.get(name));
        }
        return result;
    }
    
    private void calculate(BlackjackRepository table) {
        for (Player player : table.getPlayers().get()) {
            Result playerResult = calculatePlayerResult(player, table.getDealer());
            Result dealerResult = playerResult.toReverse();
            playersResult.put(player.getName(), playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
    }
    
    private Result calculatePlayerResult(final Player player, final Dealer dealer) {
        if (dealer.isBust()) {
            return Result.fromIsWin(!player.isBust());
        }
        if (!player.isBust() && player.isDraw(dealer)) {
            return Result.DRAW;
        }
        return Result.fromIsWin(!player.isBust() && player.isWinner(dealer));
    }
}
