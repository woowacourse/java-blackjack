package domain;

import domain.gamer.Player;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Betting {
    private final Map<Player, Money> betting;

    public Betting(final Map<Player, String> betting) {
        this.betting = new LinkedHashMap<>();
        for (final Map.Entry<Player, String> entry : betting.entrySet()) {
            this.betting.put(entry.getKey(), new Money(entry.getValue()));
        }
    }

    public BigDecimal calculateProfit(final Player player, final PlayerResult playerResult) {
        Money betMoney = betting.get(player);
        return playerResult.earn(betMoney);
    }
}
