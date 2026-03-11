package domain.result.dto;

import domain.result.GameResult;
import java.util.Map;

public record DealerGameResultDto(
        Map<GameResult, Integer> resultStatistic
) {
    public static DealerGameResultDto from(Map<GameResult, Integer> resultStatistic) {
        return new DealerGameResultDto(resultStatistic);
    }
}
