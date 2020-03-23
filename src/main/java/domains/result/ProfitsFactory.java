package domains.result;

import domains.user.Player;
import domains.user.money.ProfitMoney;

import java.util.HashMap;
import java.util.Map;

public class ProfitsFactory {
    public static Map<Player, ProfitMoney> create(GameResult gameResult) {
        Map<Player, ResultType> playerResult = gameResult.getPlayerResult();
        Map<Player, ProfitMoney> playerProfits = new HashMap<>();

        for (Player player : playerResult.keySet()) {
            ResultType resultType = playerResult.get(player);
            playerProfits.put(player, player.calculateProfitMoney(resultType));
        }

        return playerProfits;
    }
}
