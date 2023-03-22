package blackjack.domain.money;

import blackjack.domain.result.PlayerNameProfitRates;
import blackjack.domain.result.UserNameProfits;
import blackjack.domain.user.PlayerName;
import java.util.HashMap;
import java.util.Map;

public class Deposit {

    private final Map<PlayerName, BettingMoney> deposit = new HashMap<>();

    public void bet(final PlayerName playerName, final BettingMoney money) {
        deposit.put(playerName, money);
    }

    public UserNameProfits calculateProfits(final PlayerNameProfitRates playerNameAndProfitRates) {
        return playerNameAndProfitRates.calculateProfits(deposit);
    }
}
