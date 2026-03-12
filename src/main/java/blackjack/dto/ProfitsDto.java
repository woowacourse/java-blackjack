package blackjack.dto;

import java.util.Collections;
import java.util.Map;

public class ProfitsDto {

    private final double dealerProfit;
    private final Map<String, Double> playerProfits;

    public ProfitsDto(double dealerProfit, Map<String, Double> playerProfits) {
        this.dealerProfit = dealerProfit;
        this.playerProfits = playerProfits;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Double> getPlayerProfits() {
        return Collections.unmodifiableMap(playerProfits);
    }
}
