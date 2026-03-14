package blackjack.dto;

import blackjack.model.Player;
import blackjack.model.Profit;
import blackjack.model.Settlement;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitsDto {

    private final double dealerProfit;
    private final Map<String, Double> playerProfits;

    private ProfitsDto(double dealerProfit, Map<String, Double> playerProfits) {
        this.dealerProfit = dealerProfit;
        this.playerProfits = playerProfits;
    }

    public static ProfitsDto from(Settlement settlement) {
        double dealerProfitValue = settlement.getDealerProfit().getProfit();

        Map<String, Double> playerProfitsMap = new LinkedHashMap<>();
        for (Map.Entry<Player, Profit> entry : settlement.getPlayerProfits().entrySet()) {
            playerProfitsMap.put(entry.getKey().getName(), entry.getValue().getProfit());
        }

        return new ProfitsDto(dealerProfitValue, playerProfitsMap);
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Double> getPlayerProfits() {
        return playerProfits;
    }
}
