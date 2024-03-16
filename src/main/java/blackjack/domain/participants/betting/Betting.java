package blackjack.domain.participants.betting;

import blackjack.domain.participants.Player;
import blackjack.domain.participants.Result;
import java.util.Map;

public class Betting {

    private final PlayerBetting playerBetting;
    private final BettingProfit bettingProfit;

    public Betting() {
        this.playerBetting = new PlayerBetting();
        this.bettingProfit = new BettingProfit();
    }

    public void calculateProfit(Player player, Result result) {
        bettingProfit.calculateProfit(player, result, playerBetting.getBettingProfit(player));
    }

    public void bet(Player player, Profit profit) {
        playerBetting.bet(player, profit);
    }

    public Profit getProfit(Player player) {
        return bettingProfit.getProfit(player);
    }

    public Map<Player, Profit> getProfitResult() {
        return bettingProfit.getProfitResult();
    }

    public long getDealerProfit() {
        return bettingProfit.getDealerProfit();
    }
}
