package domain.result.dto;

import domain.result.WinningStatus;
import java.util.Map;

public record DealerGameResultDto(
        Map<WinningStatus, Integer> resultStatistic
) {
    public static DealerGameResultDto from(Map<WinningStatus, Integer> resultStatistic) {
        return new DealerGameResultDto(resultStatistic);
    }
}
