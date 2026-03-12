package dto;

import java.util.List;

public record BlackjackStatisticsDto(
        DealerStatisticDto dealerStatisticDto,
        List<PlayerStatisticDto> playerStatisticDtoList
) {

}
