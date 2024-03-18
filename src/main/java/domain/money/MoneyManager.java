package domain.money;

import domain.game.Result;
import domain.user.Player;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoneyManager {
    private final Map<Player, Result> playerResults;

    public MoneyManager(Map<Player, Result> playerResults) {
        this.playerResults = playerResults;
    }

    public Map<Player, Profit> calculateProfit() {
        Map<Player, Profit> profitManager = new LinkedHashMap<>();
        playerResults.forEach((player, result) -> {
                    Money money = player.getMoney();
                    profitManager.put(player, money.makeProfit(result));
                }
        );
        return Collections.unmodifiableMap(profitManager);
    }

    public Profit makeDealerProfit() {
        Profit profit = new Profit(new BigDecimal("0"));
        for (Map.Entry<Player, Profit> entries : calculateProfit().entrySet()) {
            profit = profit.sum(entries.getValue());
        }
        return profit.reverse();
    }
}
