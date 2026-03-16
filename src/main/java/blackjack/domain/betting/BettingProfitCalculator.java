package blackjack.domain.betting;

import blackjack.domain.Participants;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BettingProfitCalculator {

    private static final int DEALER_PROFIT_INVERSION = -1;

    private final DividendPolicy dividendPolicy;

    public BettingProfitCalculator(DividendPolicy dividendPolicy) {
        this.dividendPolicy = dividendPolicy;
    }

    public BettingProfit calculate(Participants participants) {
        Dealer dealer = participants.getDealer();

        Map<Player, Long> playerProfit = calculatePlayersProfit(dealer, participants.getPlayers());
        long dealerProfit = calculateDealerProfit(playerProfit);

        return new BettingProfit(playerProfit, dealerProfit);
    }

    private Map<Player, Long> calculatePlayersProfit(Dealer dealer, List<Player> players) {
        Map<Player, Long> playerProfit = new LinkedHashMap<>();

        for (Player player : players) {
            BettingResult bettingResult = BettingResult.judge(dealer, player);
            long profit = player.calculateProfit(dividendPolicy, bettingResult);
            playerProfit.put(player, profit);
        }
        return playerProfit;
    }

    private long calculateDealerProfit(Map<Player, Long> playerProfit) {
        return playerProfit.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum() * DEALER_PROFIT_INVERSION;
    }
}
