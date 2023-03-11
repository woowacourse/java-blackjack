package blackjack.domain.game;

import blackjack.domain.player.Player;
import blackjack.domain.player.Result;

import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.domain.player.Result.*;

public class BettingSystem {
    private final Map<Player, Money> betMoneyByPlayer;

    public BettingSystem(final Map<Player, Money> betMoneyByPlayer) {
        this.betMoneyByPlayer = betMoneyByPlayer;
    }

    public Map<Player, Money> getProfitResult(final Map<Player, Result> resultByPlayers) {
        Map<Player, Money> profitByPlayers = new LinkedHashMap<>();
        for (Player player : resultByPlayers.keySet()) {
            final Money income = getIncome(player, resultByPlayers.get(player));
            profitByPlayers.put(player, income);
        }
        return profitByPlayers;
    }

    private Money getIncome(final Player player, final Result result) {
        final Money money = betMoneyByPlayer.get(player);
        if (result == WIN || result == BLACKJACK) {
            final Money resultMoney = money.winMoney(player.isBlackjack());
            return resultMoney.subtract(money);
        }
        if (result == LOSE) {
            final Money resultMoney = money.loseMoney();
            return resultMoney.subtract(money);
        }
        return money.subtract(money);
    }
}
