package blackjack.domain.money;

import blackjack.domain.user.Name;
import java.util.HashMap;
import java.util.Map;

public class Deposit {

    private final Map<Name, Money> deposit = new HashMap<>();

    public void betting(final Name playerName, final Money money) {
        deposit.put(playerName, money);
    }

    public Money getProfit(final Name playerName, final Double profitRate) {
        return deposit.get(playerName).multiply(profitRate);
    }
}
