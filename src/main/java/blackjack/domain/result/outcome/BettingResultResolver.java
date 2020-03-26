package blackjack.domain.result.outcome;

import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.result.ResultType;

import java.util.List;

public class BettingResultResolver implements ResultResolver<BettingPlayer, Double, Double> {
    @Override
    public Double showPlayerResult(BettingPlayer player, ResultType type) {
        return player.computeProfit(type);
    }

    @Override
    public Double computeDealerResult(List<PlayerResult<BettingPlayer, Double, Double>> playerResults) {
        return -playerResults.stream()
                .mapToDouble(PlayerResult::showPlayerResult)
                .sum();
    }
}
