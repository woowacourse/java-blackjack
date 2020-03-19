package blackjack.domain.result;

import blackjack.domain.playing.user.Player;
import blackjack.domain.playing.user.Players;
import blackjack.domain.playing.user.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {
    private Profit dealerProfit;
    private Map<Player, Profit> playerProfit;

    public ProfitResult(Profit dealerProfit, Map<Player, Profit> playerProfit) {
        this.dealerProfit = dealerProfit;
        this.playerProfit = playerProfit;
    }

    public static ProfitResult of(Players players, User dealer) {
        Map<Player, Profit> playerProfit = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            playerProfit.put(player, Profit.of(player, dealer));
        }

        Profit dealerProfit = new Profit(0);

        return new ProfitResult(dealerProfit, playerProfit);
    }

    public Profit getDealerProfit() {
        return dealerProfit;
    }

    public Map<Player, Profit> getPlayerProfit() {
        return playerProfit;
    }
}
