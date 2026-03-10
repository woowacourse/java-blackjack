package team.blackjack.service.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import team.blackjack.domain.Result;

public record MatchResult(
        DealerResult dealerResult,
        long winCount,
        long loseCount,
        long drawCount,
        Map<String, PlayerResult> playerResultMap
) {

    public record DealerResult(
            List<Result> results
    ){
        public static DealerResult from(Collection<PlayerResult> playerResults) {
            final List<Result> dealerResults = new ArrayList<>();

            for (PlayerResult playerResult : playerResults) {
                dealerResults.add(playerResult.result().reverse());
            }

            return new DealerResult(dealerResults);
        }
    }

    public record PlayerResult(
            Result result
    ){
    }
}
