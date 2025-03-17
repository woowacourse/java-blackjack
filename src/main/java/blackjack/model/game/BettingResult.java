package blackjack.model.game;

import blackjack.model.player.Player;
import java.util.HashMap;
import java.util.Map;


public class BettingResult {

    private final Map<Player, Integer> bettingResult;

    public BettingResult(Map<Player, PlayerResult> winLoseResult) {
        Map<Player, Integer> bettingResult = new HashMap<>();
        winLoseResult.forEach((participant, result) -> {
            int betAmount = participant.getBettingMoney().getAmount();
            int payout = calculatePayout(result, betAmount);
            bettingResult.put(participant, payout);
        });
        this.bettingResult = bettingResult;
    }

    private int calculatePayout(PlayerResult result, int betAmount) {
        if (result.equals(PlayerResult.WIN)) {
            return betAmount;
        }
        if (result.equals(PlayerResult.DRAW)) {
            return 0;
        }
        if (result.equals(PlayerResult.LOSE)) {
            return -betAmount;
        } else {
            return (int) (betAmount * 1.5);
        }
    }


    public Map<Player, Integer> getBettingResult() {
        return bettingResult;
    }

    public int getDealerResult() {
        return -bettingResult.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}