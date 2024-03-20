package blackjack.domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerProfitAmounts {
    private final Map<Name, ProfitAmount> profitAmounts;

    public PlayerProfitAmounts(Map<Name, ProfitAmount> profitAmounts) {
        this.profitAmounts = profitAmounts;
    }

    public static PlayerProfitAmounts calculateProfit(Players players, Dealer dealer,
                                                      PlayerBetAmounts playerBetAmounts) {
        List<Player> playerList = players.getPlayers();
        Map<Name, ProfitAmount> profitDetails = new LinkedHashMap<>();
        for (Player player : playerList) {
            BetAmount betBetAmount = playerBetAmounts.getAmountBy(player.getName());
            double profitRate = player.calculateProfitRate(dealer.getHand());
            profitDetails.put(player.getName(), ProfitAmount.from(betBetAmount).multiplication(profitRate));
        }
        return new PlayerProfitAmounts(profitDetails);
    }

    public ProfitAmount calculateDealerProfit() {
        return profitAmounts.values().stream()
                .reduce(new ProfitAmount(0), ProfitAmount::add)
                .inverse();
    }

    public Map<Name, ProfitAmount> getProfitAmounts() {
        return profitAmounts;
    }
}
