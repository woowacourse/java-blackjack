package blackjack.domain.result;

import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
import blackjack.domain.participant.Participant;
import java.util.HashMap;
import java.util.Map;

public final class ResultStatistic {
    private final Map<String, Result> playersResult = new HashMap<>();
    private final Map<Result, Integer> dealerResults = new HashMap<>();

    private ResultStatistic(final Participant participant) {
        for (Result value : Result.values()) {
            dealerResults.put(value, 0);
        }
        calculate(participant);
    }

    public static ResultStatistic from(final Participant participant) {
        return new ResultStatistic(participant);
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

    private void calculate(final Participant participant) {
        for (Player player : participant.getRawPlayers()) {
            Result playerResult = calculatePlayerResult(player, participant.getDealer());
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
