package blackjack.domain.betting;

import blackjack.domain.cardgame.CardGameResult;
import blackjack.domain.cardgame.WinningStatus;
import blackjack.domain.player.Name;

import java.util.LinkedHashMap;
import java.util.Map;

public class BetDetails {
    private final Map<Name, Money> playersBettingMoney;

    public BetDetails(final Map<Name, Money> playersBettingMoney) {
        this.playersBettingMoney = playersBettingMoney;
    }

    public ProfitDetails calculateProfit(final CardGameResult result) {
        final Map<Name, Profit> map = new LinkedHashMap<>();

        for (final var entries : result.totalResult().entrySet()) {
            final Name name = entries.getKey();
            final WinningStatus status = entries.getValue();
            final Money money = findBettingMoney(name);
            map.put(name, Profit.of(money, status));
        }

        return new ProfitDetails(map);
    }

    private Money findBettingMoney(final Name name) {
        return playersBettingMoney.get(name);
    }
}
