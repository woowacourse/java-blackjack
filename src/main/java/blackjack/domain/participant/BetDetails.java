package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetDetails {
    private final Map<Name, Money> betDetails;

    public BetDetails(Map<Name, Money> betDetails) {
        this.betDetails = betDetails;
    }

    public ProfitDetails calculateProfit(Players players, Dealer dealer) {
        List<Player> playerList = players.getPlayers();
        Map<Name, Profit> profitDetails = new LinkedHashMap<>();
        for (Player player : playerList) {
            Money betMoney = betDetails.get(player.getName());
            double profitRate = player.calculateProfitRate(dealer.getHand());
            profitDetails.put(player.getName(), Profit.from(betMoney).multiplication(profitRate));
        }
        return new ProfitDetails(profitDetails);
    }
}
