package blackjack.dto;

import blackjack.domain.ProfitResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResultDto {

    private final double dealerProfit;
    private final Map<String, Double> playersProfit;

    private ProfitResultDto(double dealerProfit, Map<String, Double> playersProfit) {
        this.dealerProfit = dealerProfit;
        this.playersProfit = new LinkedHashMap<>(playersProfit);
    }

    public static ProfitResultDto from(ProfitResult profitResult) {
        return new ProfitResultDto(profitResult.getDealerProfit(), profitResult.getPlayersProfit());
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Double> getPlayersProfit() {
        return playersProfit;
    }
}
