package blackjack.domain.game;

import blackjack.domain.player.Player;
import blackjack.domain.player.Result;

import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.domain.player.Result.*;

public class BettingSystem {
    private final Map<Player, BetMoney> betMoneyByPlayer;

    public BettingSystem(final Map<Player, BetMoney> betMoneyByPlayer) {
        this.betMoneyByPlayer = betMoneyByPlayer;
    }

    public Map<Player, BetMoney> getProfitResult(final Map<Player, Result> resultByPlayers) {
        Map<Player, BetMoney> profitByPlayers = new LinkedHashMap<>();
        for (Player player : resultByPlayers.keySet()) {
            final BetMoney income = getIncome(player, resultByPlayers.get(player));
            profitByPlayers.put(player, income);
        }
        return profitByPlayers;
    }

    private BetMoney getIncome(final Player player, final Result result) {
        final BetMoney betMoney = betMoneyByPlayer.get(player);
        if (result == WIN || result == BLACKJACK) {
            final BetMoney resultMoney = betMoney.winMoney(player.isBlackjack());
            return resultMoney.subtract(betMoney);
        }
        if (result == LOSE) {
            final BetMoney resultMoney = betMoney.loseMoney();
            return resultMoney.subtract(betMoney);
        }
        return betMoney.subtract(betMoney);
    }
}
