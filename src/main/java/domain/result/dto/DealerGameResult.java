package domain.result.dto;

import domain.result.GameResult;
import java.util.Map;

public record DealerGameResult(
        Map<GameResult, Integer> resultStatistic
) {
    public static DealerGameResult from(Map<GameResult, Integer> resultStatistic) {
        return new DealerGameResult(resultStatistic);
    }
}
