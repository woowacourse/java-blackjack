package domains.result;

import domains.user.Player;
import domains.user.money.ProfitMoney;

import java.util.HashMap;
import java.util.Map;

public class Profits {
    private Map<Player, ProfitMoney> playerProfits;

    public Profits(GameResult gameResult) {
        playerProfits = new HashMap<>();
        create(gameResult.getPlayerResult());
    }

    private void create(Map<Player, ResultType> gameResult) {
        for (Player player : gameResult.keySet()) {
            ResultType resultType = gameResult.get(player);
            playerProfits.put(player, resultType.calculateProfitMoney(player));
        }
    }

    public ProfitMoney createDealerProfit() {
        return ProfitMoney.calculateDealerProfit(playerProfits.values());
    }

    public Map<Player, ProfitMoney> getPlayerProfits() {
        return playerProfits;
    }
}
