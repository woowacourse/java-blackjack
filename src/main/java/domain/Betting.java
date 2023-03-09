package domain;

import java.util.Map;

public class Betting {

    private final Map<Player, Money> betting;

    public Betting(Map<Player, Money> betting) {
        this.betting = betting;
    }

    public int getBettingMoney(Player player) {
        return betting.get(player).getValue();
    }

}
