package domain.result;

import domain.pariticipant.Player;

import java.util.Map;

public class BettingProfit {
    private final Map<Player, Integer> bettingResult;

    public BettingProfit(Map<Player, Integer> bettingResult) {
        this.bettingResult = bettingResult;
    }
}
