package blackjack.domain.result;

import blackjack.domain.money.Money;
import blackjack.domain.user.DealerName;
import blackjack.domain.user.Name;
import blackjack.domain.user.PlayerName;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class UserNameProfits {

    private final Map<Name, Money> userNameProfitMapper;

    private UserNameProfits(final Map<Name, Money> playerNameProfitMapper) {
        this.userNameProfitMapper = Collections.unmodifiableMap(playerNameProfitMapper);
    }

    public static UserNameProfits addDealerProfit(final Map<PlayerName, Money> playerNameProfitMapper) {
        final Map<Name, Money> userNameProfit = new LinkedHashMap<>();
        final Money dealerProfit = playerNameProfitMapper.values()
                .stream()
                .reduce(new Money(0), Money::sum)
                .opposite();
        userNameProfit.put(new DealerName(), dealerProfit);
        userNameProfit.putAll(playerNameProfitMapper);
        return new UserNameProfits(userNameProfit);
    }

    public Map<Name, Money> getUserNameProfitMapper() {
        return userNameProfitMapper;
    }
}
