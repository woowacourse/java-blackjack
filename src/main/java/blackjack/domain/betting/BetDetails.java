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
        final Map<Name, Profit> profitDetails = new LinkedHashMap<>();

        for (final var nameAndStatus : result.totalResult().entrySet()) {
            final Name name = extractName(nameAndStatus);
            final WinningStatus status = extractStatus(nameAndStatus);
            final Profit profit = Profit.of(findBettingMoney(name), status);
            profitDetails.put(name, profit);
        }

        return new ProfitDetails(profitDetails);
    }

    private Name extractName(final Map.Entry<Name, WinningStatus> nameAndStatus) {
        return nameAndStatus.getKey();
    }

    private WinningStatus extractStatus(final Map.Entry<Name, WinningStatus> nameAndStatus) {
        return nameAndStatus.getValue();
    }

    private Money findBettingMoney(final Name name) {
        return playersBettingMoney.get(name);
    }
}
