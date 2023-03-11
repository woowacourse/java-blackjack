package blackjack.domain.game;

import blackjack.domain.player.Player;
import blackjack.domain.player.Result;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingSystem {
    private final Map<Player, Money> betMoneyByPlayer;

    public BettingSystem(final Map<Player, Money> betMoneyByPlayer) {
        this.betMoneyByPlayer = betMoneyByPlayer;
    }

    public Map<Player, Money> getProfitResult(final Map<Player, Result> resultByPlayers) {
        Map<Player, Money> profitByPlayers = new LinkedHashMap<>();
        for (Player player : resultByPlayers.keySet()) {
            final Money income = getProfit(player, resultByPlayers.get(player));
            profitByPlayers.put(player, income);
        }
        return profitByPlayers;
    }

    private Money getProfit(final Player player, final Result result) {
        final Money money = betMoneyByPlayer.get(player);
        return money.calculateProfit(result.getPayoutRatio());
    }
}
