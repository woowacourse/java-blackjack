package blackjack.dto;

import blackjack.domain.result.PlayerResults;
import java.util.List;

public record GameResultDtos(
    DealerResultDto dealerResultDto,
    List<GameResultDto> gameResultDtos
) {
    public static GameResultDtos of(PlayerResults results) {
        List<GameResultDto> gameResultDtos = results.entries().entrySet().stream()
            .map(entry -> GameResultDto.of(entry.getKey(), entry.getValue()))
            .toList();

        DealerResultDto dealerResultDto = DealerResultDto.from(results);
        return new GameResultDtos(dealerResultDto, gameResultDtos);
    }
}
