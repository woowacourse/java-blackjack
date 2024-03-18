package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerBetAmounts {
    private final Map<Name, BetAmount> betAmounts;

    public PlayerBetAmounts(Map<Name, BetAmount> betAmounts) {
        this.betAmounts = betAmounts;
    }

    public PlayerProfitAmounts calculateProfit(Players players, Dealer dealer) {
        List<Player> playerList = players.getPlayers();
        Map<Name, ProfitAmount> profitDetails = new LinkedHashMap<>();
        for (Player player : playerList) {
            BetAmount betBetAmount = betAmounts.get(player.getName());
            double profitRate = player.calculateProfitRate(dealer.getHand());
            profitDetails.put(player.getName(), ProfitAmount.from(betBetAmount).multiplication(profitRate));
        }
        return new PlayerProfitAmounts(profitDetails);
    }
}
