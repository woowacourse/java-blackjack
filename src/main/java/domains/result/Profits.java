package domains.result;

import domains.user.Money;
import domains.user.Player;

import java.util.HashMap;
import java.util.Map;

public class Profits {
    private Map<Player, Money> profits;

    public Profits(GameResult gameResult) {
        Map<Player, ResultType> playerResult = gameResult.getPlayerResult();
        profits = new HashMap<>();
        for (Player player : playerResult.keySet()) {
            ResultType resultType = playerResult.get(player);
            Money profit = player.getBettingMoney().multiply(resultType.getProfitRate());
            profits.put(player, profit);
        }
    }

    public Map<Player, Money> getProfits() {
        return profits;
    }
}
