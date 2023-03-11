package blackjack.domain.betting;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingResult {

    int dealerBettingResult;
    private final Map<Player, Integer> playersBettingResults = new HashMap<>();

    public BettingResult(Map<Player, Double> playersYields, BettingAreas bettingAreas) {
        playersYields.keySet().forEach(player -> {
                    int bettingResult = calculateBetting(playersYields.get(player), bettingAreas.getPlayerBettingAmount(player));
                    playersBettingResults.put(player, bettingResult);
                }
        );
    }

    private int calculateBetting(double yield, int bettingAmount) {
        return (int) yield * bettingAmount;
    }

    public int getDealerBettingResult() {
        dealerBettingResult = 0;

        playersBettingResults.values().forEach(value ->
                dealerBettingResult -= value);

        return dealerBettingResult;
    }

    public int getPlayerBettingResult(Player player) {
        return playersBettingResults.get(player);
    }
}
