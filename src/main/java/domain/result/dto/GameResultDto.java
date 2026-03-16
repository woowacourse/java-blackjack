package domain.result.dto;

import java.util.List;

public record GameResultDto(
        DealerGameResultDto dealerGameResultDto,
        List<PlayerGameResultDto> playerGameResultDtos
) {
    public static GameResultDto of(DealerGameResultDto dealerGameResultDto,
                                   List<PlayerGameResultDto> playerGameResultDtos) {
        return new GameResultDto(dealerGameResultDto, playerGameResultDtos);
    }
}
