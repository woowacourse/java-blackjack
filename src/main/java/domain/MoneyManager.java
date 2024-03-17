package domain;

import domain.game.Result;
import domain.user.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoneyManager {
    private final Map<Player, Money> bettingManager;

    public MoneyManager(Map<Player, Money> bettingManager) {
        this.bettingManager = bettingManager;
    }

    public Map<Player, Profit> calculateProfit(Map<Player, Result> playerResults) {
        Map<Player, Profit> profitManager = new LinkedHashMap<>();
        playerResults.forEach((player, result) -> {
                    Money money = bettingManager.get(player);
                    profitManager.put(player, money.makeProfit(result));
                }
        );
        return Collections.unmodifiableMap(profitManager);
    }
}
