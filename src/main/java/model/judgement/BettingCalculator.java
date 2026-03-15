package model.judgement;

import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.BettingPlayer;

public class BettingCalculator {

    public Map<BettingPlayer, Profit> calculateProfits(PlayerResult playerResult) {
        Map<BettingPlayer, Profit> profits = new LinkedHashMap<>();
        playerResult.result().forEach((player, status) -> {
            if (player instanceof BettingPlayer bettingPlayer) {
                profits.put(bettingPlayer, bettingPlayer.calculateProfit(status));
            }
        });
        return profits;
    }

    public Profit calculateDealerProfit(Map<BettingPlayer, Profit> profits) {
        return profits.values().stream()
                .reduce(Profit.ZERO, (sum, profit) -> sum.add(profit.negate()));
    }
}
