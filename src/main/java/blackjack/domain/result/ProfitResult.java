package blackjack.domain.result;

import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProfitResult {
    private Profit dealerProfit;
    private Map<Player, Profit> playerProfit;

    private ProfitResult(Profit dealerProfit, Map<Player, Profit> playerProfit) {
        this.dealerProfit = dealerProfit;
        this.playerProfit = playerProfit;
    }

    public static ProfitResult of(Players players, User dealer) {
        Map<Player, Profit> playerProfit = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            playerProfit.put(player, Profit.of(player, dealer));
        }

        List<Profit> profits = new ArrayList<>(playerProfit.values());
        Profit dealerProfit = Profit.sum(profits).opposite();

        return new ProfitResult(dealerProfit, playerProfit);
    }

    public Profit getDealerProfit() {
        return dealerProfit;
    }

    public Map<Player, Profit> getPlayerProfit() {
        return playerProfit;
    }
}
