package domain.bet;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.result.WinLossResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingPool {

    private final Map<Player, BetMoney> bettingPool;

    public BettingPool(final Map<Player, BetMoney> bettingPool) {
        this.bettingPool = new LinkedHashMap<>(bettingPool);
    }

    public Map<String, BetMoney> computePlayersProfit(Dealer dealer) {
        Map<String, BetMoney> playerProfits = new LinkedHashMap<>();
        for (Map.Entry<Player, BetMoney> playersBetMoney : bettingPool.entrySet()) {
            Player player = playersBetMoney.getKey();
            BetMoney betMoney = playersBetMoney.getValue();

            playerProfits.put(player.getName(), betMoney.computeProfit(WinLossResult.from(dealer, player)));
        }
        return playerProfits;
    }
}
