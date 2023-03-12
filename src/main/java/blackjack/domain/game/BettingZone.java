package blackjack.domain.game;

import blackjack.domain.player.Player;
import blackjack.domain.player.Result;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingZone {
    private final Map<Player, Money> betMoneyByPlayers;

    public BettingZone(final Map<Player, Money> betMoneyByPlayers) {
        this.betMoneyByPlayers = betMoneyByPlayers;
    }

    public Map<Player, Money> calculateProfitByPlayers(final Map<Player, Result> resultByPlayers) {
        Map<Player, Money> profitByPlayers = new LinkedHashMap<>();

        for (Player player : resultByPlayers.keySet()) {
            profitByPlayers.put(player, calculateProfit(player, resultByPlayers.get(player)));
        }

        return profitByPlayers;
    }

    private Money calculateProfit(final Player player, final Result result) {
        final Money money = betMoneyByPlayers.get(player);
        return money.multiply(result.getPayoutRatio());
    }
}
