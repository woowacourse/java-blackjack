package blackjack.dto;

import java.util.Map;

public class UserProfitDto {
    private final double dealerProfit;
    private final Map<String, Double> playerProfit;

    public UserProfitDto(double dealerProfit, Map<String, Double> playerProfit) {
        this.dealerProfit = dealerProfit;
        this.playerProfit = playerProfit;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Double> getPlayerProfit() {
        return playerProfit;
    }

}
