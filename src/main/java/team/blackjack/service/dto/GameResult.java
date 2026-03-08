package team.blackjack.service.dto;

import java.util.List;
import java.util.Map;
import team.blackjack.domain.Result;

public record GameResult(
        DealerResult dealerResult,
        Map<String, PlayerResult> playerResultMap
) {
    public record DealerResult(
            List<Result> results
    ){
        public long countBy(Result target) {
            return results.stream()
                    .filter(result -> result == target)
                    .count();
        }
    }

    public record PlayerResult(
            Result result
    ){
    }
}
