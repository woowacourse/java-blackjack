package blackjack.dto;

import blackjack.domain.batting.BettingResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResultDto {

    private final Map<PlayerDto, Integer> gamersProfitResults;
    private final int dealerProfit;

    private BettingResultDto(Map<PlayerDto, Integer> profitResults, int dealerProfit) {
        this.gamersProfitResults = profitResults;
        this.dealerProfit = dealerProfit;
    }

    public static BettingResultDto from(BettingResult bettingResult) {
        Map<PlayerDto, Integer> gamersProfitResults = new LinkedHashMap<>();
        bettingResult.getGamersProfit()
            .forEach((player, money) -> {
                gamersProfitResults.computeIfAbsent(PlayerDto.from(player), (key) -> money.toInt());
            });
        int dealerProfit = bettingResult.getDealerProfit().toInt();
        return new BettingResultDto(gamersProfitResults, dealerProfit);
    }

    public Map<PlayerDto, Integer> getGamersProfitResults() {
        return gamersProfitResults;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
