package dto;

import domain.bet.BetProfit;
import java.util.List;

public record BetProfitDto(long dealerProfit, List<PlayerProfitDto> playerProfits) {

    public static BetProfitDto from(BetProfit betProfit) {
        return new BetProfitDto(
                betProfit.getDealerBetProfit().amount(),
                betProfit.getPlayerBetProfit().entrySet().stream()
                        .map(entry -> PlayerProfitDto.from(entry.getKey(), entry.getValue()))
                        .toList()
        );
    }
}
