package dto;

import java.util.List;

public record BlackjackStatisticsDto(
        DealerResultDto dealerResultDto,
        List<PlayerResultDto> playerResultDtoList
) {

}
