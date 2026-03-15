package blackjack.dto;

import blackjack.domain.participants.Player;
import blackjack.domain.participants.Profit;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public record GameResultDtos(
    DealerProfitDto dealerProfitDto,
    List<PlayerProfitDto> playerProfitDtos
) {
    public static GameResultDtos of(Map<Player, Profit> playerProfits) {
        List<PlayerProfitDto> playerProfitDtos = playerProfits.entrySet().stream()
            .map(GameResultDtos::convert)
            .toList();

        DealerProfitDto dealerProfitDto = DealerProfitDto.from(playerProfits);
        return new GameResultDtos(dealerProfitDto, playerProfitDtos);
    }

    private static PlayerProfitDto convert(Entry<Player, Profit> playerProfit) {
        return PlayerProfitDto.of(playerProfit.getKey(), playerProfit.getValue());
    }
}
