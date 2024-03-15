package blackjack.domain.participants;

import blackjack.domain.Profit;
import java.util.Map;

public class Betting {

    private final BettingRepository bettingRepository;
    private final BettingProfit bettingProfit;

    public Betting() {
        this.bettingRepository = new BettingRepository();
        this.bettingProfit = new BettingProfit();
    }

    public void calculateProfit(Player player, State state) {
        bettingProfit.calculateProfit(player, state, bettingRepository.getBettingProfit(player));
    }

    public void bet(Player player, Profit profit) {
        bettingRepository.bet(player, profit);
    }

    public Profit getProfit(Player player) {
        return bettingProfit.getProfit(player);
    }

    public Map<Player, Profit> getProfitResult() {
        return bettingProfit.getProfitResult();
    }

    public int getDealerProfit() {
        return bettingProfit.getDealerProfit();
    }
}
