package dto;

import java.util.List;

public record ResultDto(
        List<StatisticsDto> statisticsDtos,
        String dealerName
) {
}

