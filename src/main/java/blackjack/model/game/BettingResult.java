package blackjack.model.game;

import blackjack.model.player.Player;
import java.util.HashMap;
import java.util.Map;


public class BettingResult {

    private final Map<Player, Double> bettingResult;

    public BettingResult(Map<Player, PlayerResult> winLoseResult) {
        Map<Player, Double> bettingResult = new HashMap<>();
        winLoseResult.forEach((player, result) -> {
            int betAmount = player.getBettingMoney().getAmount();
            double payout = result.calculatePayout(betAmount);
            bettingResult.put(player, payout);
        });
        this.bettingResult = bettingResult;
    }


    public Map<Player, Double> getBettingResult() {
        return bettingResult;
    }

    public int getDealerResult() {
        return -bettingResult.values().stream()
                .mapToInt(Double::intValue)
                .sum();
    }
}