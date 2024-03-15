package dto;

import domain.gamer.Player;

import java.math.BigDecimal;
import java.util.Map;

public class TotalResult {
    private final DealerProfit dealerProfit;
    private final PlayerProfits playerResult;

    public TotalResult(final DealerProfit dealerProfit, final PlayerProfits playerResult) {
        this.dealerProfit = dealerProfit;
        this.playerResult = playerResult;
    }

    public String getDealerProfit() {
        return dealerProfit.getProfit().toString();
    }

    public Map<Player, BigDecimal> getPlayerResult() {
        return playerResult.getResults();
    }
}
