package blackjack.domain.betting;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetDetails {
    private final Map<Name, Money> playersBettingMoney;

    public BetDetails(final Map<Name, Money> playersBettingMoney) {
        this.playersBettingMoney = playersBettingMoney;
    }

    // TODO: 테스트 추가
    public ProfitDetails calculateProfit(final Dealer dealer, final List<Player> players) {
        final Map<Name, Profit> profitDetails = new LinkedHashMap<>();

        for (final Player player : players) {
            final Name name = player.name();
            final GameResult result = GameResult.doesPlayerWin(dealer, player);
            final Profit profit = Profit.of(findBettingMoney(name), result);
            profitDetails.put(name, profit);
        }

        return new ProfitDetails(profitDetails);
    }

    private Money findBettingMoney(final Name name) {
        return playersBettingMoney.get(name);
    }
}
