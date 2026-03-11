package domain.result;

import domain.pariticipant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public record PlayerMatchResult(Map<Player, MatchCase> playerMatchResult) {

    public BettingProfit calculateBettingProfit() {
        Map<Player, Integer> bettingResult = new LinkedHashMap<>();

        for (Player player : playerMatchResult.keySet()) {
            int profit = player.calculateBettingProfit(playerMatchResult.get(player));
            bettingResult.put(player, profit);
        }
        return new BettingProfit(bettingResult);
    }
}
