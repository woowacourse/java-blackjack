package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    final Map<Player, Money> bank = new HashMap<>();

    public void bet(final Player player, final Money money) {
        bank.put(player, money);
    }

    public Money getDealerProfit(final GameResult gameResult) {
        Money money = Money.init();
        for (Player player : bank.keySet()) {
            money = money.sumDealerProfit(getProfit(gameResult, player));
        }
        return money;
    }

    public Money getProfit(final GameResult gameResult, final Player player) {
        return gameResult.calculateProfit(player, bank.get(player));
    }
}
