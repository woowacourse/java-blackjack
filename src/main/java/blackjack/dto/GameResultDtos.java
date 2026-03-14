package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import java.util.List;
import java.util.Map;

public record GameResultDtos(
    DealerResultDto dealerResultDto,
    List<GameResultDto> gameResultDtos
) {
    public static GameResultDtos of(Map<Player, GameResult> results) {
        List<GameResultDto> gameResultDtos = results.entrySet().stream()
            .map(entry -> GameResultDto.of(entry.getKey(), entry.getValue()))
            .toList();

        DealerResultDto dealerResultDto = DealerResultDto.from(results);
        return new GameResultDtos(dealerResultDto, gameResultDtos);
    }
}
