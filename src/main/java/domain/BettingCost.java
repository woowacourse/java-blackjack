package domain;

import domain.participant.Player;

import java.util.Map;

public class BettingCost {
    private final Map<Player, Integer> revenue;

    public BettingCost(Map<Player, Integer> revenue) {
        this.revenue = revenue;
    }


}
