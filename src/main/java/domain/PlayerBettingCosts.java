package domain;

import domain.participant.Player;

import java.util.Map;

public class PlayerBettingCosts {
    private final Map<Player, Money> bettingCosts;

    public PlayerBettingCosts(Map<Player, Money> bettingCosts) {
        this.bettingCosts = bettingCosts;
    }

    public int getCostBy(Player player) {
        if (!bettingCosts.containsKey(player)) {
            throw new IllegalArgumentException("");
        }
        Money money = bettingCosts.get(player);
        return money.value();
    }
}
