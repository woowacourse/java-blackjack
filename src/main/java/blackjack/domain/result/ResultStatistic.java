package blackjack.domain.result;

import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
import java.util.HashMap;
import java.util.Map;

public final class ResultStatistic {
    private final Map<String, Result> playersResult = new HashMap<>();
    private final Map<Result, Integer> dealerResults = new HashMap<>();

    public ResultStatistic(final Players players, Dealer dealer) {
        for (Result value : Result.values()) {
            dealerResults.put(value, 0);
        }
        calculate(players, dealer);
    }

    public static ResultStatistic of(final Players players, Dealer dealer) {
        return new ResultStatistic(players, dealer);
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

    private void calculate(final Players players, Dealer dealer) {
        for (Player player : players.get()) {
            Result playerResult = calculatePlayerResult(player, dealer);
            Result dealerResult = playerResult.toReverse();
            playersResult.put(player.getName(), playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
    }

    private Result calculatePlayerResult(final Player player, final Dealer dealer) {
        if (dealer.isBust()) {
            return Result.fromBoolean(!player.isBust());
        }
        if (!player.isBust() && player.isDraw(dealer)) {
            return Result.DRAW;
        }
        return Result.fromBoolean(!player.isBust() && player.isWinner(dealer));
    }
}
