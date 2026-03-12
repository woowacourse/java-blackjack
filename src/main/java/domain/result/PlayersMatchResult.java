package domain.result;

import domain.pariticipant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public record PlayersMatchResult(Map<Player, MatchCase> playerMatchResult) {

    public PlayersBettingProfit calculateBettingProfit() {
        Map<Player, Long> bettingResult = new LinkedHashMap<>();

        for (Player player : playerMatchResult.keySet()) {
            long profit = player.calculateBettingProfit(playerMatchResult.get(player));
            bettingResult.put(player, profit);
        }
        return new PlayersBettingProfit(bettingResult);
    }
}
