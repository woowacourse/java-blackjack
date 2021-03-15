package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private Money dealerProfit = new Money(0);
    private final Map<String, Double> playerProfits = new LinkedHashMap<>();

    public Result(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            Money playerProfit = player.getProfit(dealer);
            playerProfits.put(player.getName(), playerProfit.getMoney());
            dealerProfit = dealerProfit.minusProfit(playerProfit);
        }
    }

    public double getDealerProfit() {
        return dealerProfit.getMoney();
    }

    public Map<String, Double> getPlayerProfits() {
        return playerProfits;
    }
}
