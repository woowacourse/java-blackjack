package blackjack.domain.result;

import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Money;
import blackjack.domain.user.PlayerName;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerNameProfitRates {

    private final Map<PlayerName, Double> nameProfitRateMapper;

    public PlayerNameProfitRates(final Map<PlayerName, Double> nameProfitRateMapper) {
        this.nameProfitRateMapper = Collections.unmodifiableMap(nameProfitRateMapper);
    }

    public UserNameProfits calculateProfits(final Map<PlayerName, BettingMoney> deposit) {
        final Map<PlayerName, Money> playerNameProfits = new LinkedHashMap<>();
        deposit.forEach((key, value) -> playerNameProfits
                .put(key, value.multiply(nameProfitRateMapper.get(key))));
        return UserNameProfits.withDealerProfit(playerNameProfits);
    }
}
