package blackjack.domain.betting;

import blackjack.domain.participant.Player;

import java.util.Map;

public class BettingAreas {

    private final Map<Player, BettingAmount> bettingAreas;

    public BettingAreas(Map<Player, BettingAmount> bettingAreas) {
        this.bettingAreas = bettingAreas;
    }

    public BettingAmount getPlayerBettingAmount(Player player) {
        return bettingAreas.get(player);
    }
}
