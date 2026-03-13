package dto;

import java.util.List;

public record BlackjackStatisticsDto(
        int dealerProfit,
        List<PlayerStatisticDto> playerStatisticDtoList
) {

    public static BlackjackStatisticsDto of(int dealerProfit, List<PlayerStatisticDto> playerStatisticDtoList) {
        return new BlackjackStatisticsDto(dealerProfit, playerStatisticDtoList);
    }
}
