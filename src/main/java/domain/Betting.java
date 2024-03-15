package domain;

import domain.gamer.Player;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Betting {
    private final Map<Player, Money> betting;

    public Betting(final Map<Player, Money> betting) {
        this.betting = new HashMap<>(betting);
    }

    public BigDecimal calculateProfit(final Player player, final PlayerResult playerResult) {
        Money betMoney = betting.get(player);
        return playerResult.earn(betMoney);
    }
}
