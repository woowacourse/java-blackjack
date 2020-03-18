package domains.result;

import domains.user.Money;
import domains.user.Player;

import java.util.HashMap;
import java.util.Map;

public class Profits {
    private Map<Player, Money> playerProfits;
    private Money dealerProfit;

    public Profits(Map<Player, ResultType> playerResult) {
        playerProfits = new HashMap<>();
        for (Player player : playerResult.keySet()) {
            ResultType resultType = playerResult.get(player);
            Money profit = player.getBettingMoney().multiply(resultType.getProfitRate());
            playerProfits.put(player, profit);
        }
        dealerProfit = calculateDealerProfit();
    }

    private Money calculateDealerProfit() {
        Money amount = new Money(0);
        for (Money money : playerProfits.values()) {
            amount = amount.minus(money);
        }
        return amount;
    }

    public Map<Player, Money> getPlayerProfits() {
        return playerProfits;
    }

    public Money getDealerProfit() {
        return dealerProfit;
    }
}
