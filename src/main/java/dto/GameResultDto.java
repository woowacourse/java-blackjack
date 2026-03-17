package dto;

import java.util.List;

public record GameResultDto(
        DealerResultDto dealerResultDto, List<PlayerResultDto> playerResultDto
) {
}
