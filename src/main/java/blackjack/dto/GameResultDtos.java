package blackjack.dto;

import blackjack.domain.participants.Player;
import blackjack.domain.participants.Profit;
import java.util.List;
import java.util.Map;

public record GameResultDtos(
    DealerProfitDto dealerProfitDto,
    List<PlayerProfitDto> playerProfitDtos
) {
    public static GameResultDtos of(Map<Player, Profit> results) {
        List<PlayerProfitDto> playerProfitDtos = results.entrySet().stream()
            .map(entry -> PlayerProfitDto.of(entry.getKey(), entry.getValue().value()))
            .toList();

        DealerProfitDto dealerProfitDto = DealerProfitDto.from(results);
        return new GameResultDtos(dealerProfitDto, playerProfitDtos);
    }
}
