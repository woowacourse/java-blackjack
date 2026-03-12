package blackjack.model;

import blackjack.dto.ProfitsDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class Profits {

    private final Map<Player, GameResult> results;

    public Profits(Map<Player, GameResult> results) {
        this.results = results;
    }

    public ProfitsDto toDto() {
        double dealerProfit = calculateDealerProfit();

        Map<String, Double> playerProfits = new LinkedHashMap<>();
        for (Player player : results.keySet()) {
            playerProfits.put(player.getName(), calculatePlayerProfit(player));
        }

        return new ProfitsDto(dealerProfit, playerProfits);
    }

    public double calculateDealerProfit() {
        double dealerProfit = 0;
        for (Player player : results.keySet()) {
            dealerProfit += -player.getBettingAmount(results.get(player));
        }
        return dealerProfit;
    }

    public double calculatePlayerProfit(Player player) {
        return player.getBettingAmount(results.get(player));
    }
}
