package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class Betting {

    final Map<Player, BettingMoney> betting = new HashMap<>();

    public void bet(final Player player, final BettingMoney money) {
        betting.put(player, money);
    }

    public BettingMoney getDealerProfit(final GameResult gameResult) {
        BettingMoney money = BettingMoney.init();
        for (Player player : betting.keySet()) {
            money = money.subtractBettingMoney(getProfit(gameResult, player));
        }
        return money;
    }

    public BettingMoney getProfit(final GameResult gameResult, final Player player) {
        return gameResult.calculateProfit(player, betting.get(player));
    }
}
