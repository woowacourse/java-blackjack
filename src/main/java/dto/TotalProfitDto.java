package dto;

import java.util.Map;

public class TotalProfitDto {
    private final Map<String, Long> playerProfits;
    private final long dealerProfit;

    public TotalProfitDto(Map<String, Long> playerProfits, long dealerProfit) {
        this.playerProfits = playerProfits;
        this.dealerProfit = dealerProfit;
    }

    public Map<String, Long> getPlayerProfits() {
        return playerProfits;
    }

    public long getDealerProfit() {
        return dealerProfit;
    }
}
