package blackjack.dto;

import java.util.List;

public record GameResultDto(
    DealerResultDto dealerResultDto,
    List<GameResultDtos> gameResultDtos
) {
}
