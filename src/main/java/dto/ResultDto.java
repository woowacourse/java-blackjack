package dto;

import java.util.List;

public record ResultDto(
        DealerResultDto dealerResultDto,
        List<PlayerResultDto> playerResultDtos
) {
    public static ResultDto of(DealerResultDto dealerResultDto, List<PlayerResultDto> playerResultDtos) {
        return new ResultDto(dealerResultDto, playerResultDtos);
    }
}
