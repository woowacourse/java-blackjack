package domain.betting;

import domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bettings {

    private Map<Player, Money> bettings;

    public Bettings() {
        this.bettings = new LinkedHashMap<>();
    }

    public void bet(Player player, Money money) {
        bettings.put(player, money);
    }

    public Money getPlayerBettingMoney(Player player) {
        return bettings.get(player);
    }

}
