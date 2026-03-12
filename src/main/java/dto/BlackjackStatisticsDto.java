package dto;

import java.util.List;

public record BlackjackStatisticsDto(
        DealerStatisticDto dealerStatisticDto,
        List<PlayerStatisticDto> playerStatisticDtoList
) {

    public static BlackjackStatisticsDto of(DealerStatisticDto dealerStatisticDto, List<PlayerStatisticDto> playerStatisticDtoList) {
        return new BlackjackStatisticsDto(dealerStatisticDto, playerStatisticDtoList);
    }
}
