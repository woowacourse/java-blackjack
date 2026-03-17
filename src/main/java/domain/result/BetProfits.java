package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public record BetProfits(
        BetProfit dealerProfit,
        List<BetProfit> betProfits
) {
    public static BetProfits from(final Dealer dealer, final List<Player> players) {
        final List<BetProfit> profits = new ArrayList<>();
        int dealerTotal = 0;

        for (final Player player : players) {
            final GameResult result = GameResult.judge(dealer, player);

            final BetProfit profit = BetProfit.from(player, result);
            profits.add(profit);

            dealerTotal -= profit.profit();
        }
        
        final BetProfit dealerProfit = new BetProfit(dealer.getName(), dealerTotal);

        return new BetProfits(dealerProfit, profits);
    }
}
