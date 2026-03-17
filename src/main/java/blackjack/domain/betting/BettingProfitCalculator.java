package blackjack.domain.betting;

import blackjack.domain.GameResult;
import blackjack.domain.Participants;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingProfitCalculator {

    private static final int DEALER_PROFIT_INVERSION = -1;

    private final DividendPolicy dividendPolicy;

    public BettingProfitCalculator(DividendPolicy dividendPolicy) {
        this.dividendPolicy = dividendPolicy;
    }

    public BettingProfit calculate(Participants participants) {
        Map<Player, Long> playerProfit = calculatePlayersProfit(participants);
        long dealerProfit = calculateDealerProfit(playerProfit);
        return new BettingProfit(playerProfit, dealerProfit);
    }

    private Map<Player, Long> calculatePlayersProfit(Participants participants) {
        Map<Player, Long> playerProfit = new LinkedHashMap<>();

        participants.withDealer(dealer ->
                participants.forEachPlayer(player -> {
                    GameResult gameResult = GameResult.judge(dealer, player);
                    long profit = player.calculateProfit(dividendPolicy, gameResult);
                    playerProfit.put(player, profit);
                })
        );
        return playerProfit;
    }

    private long calculateDealerProfit(Map<Player, Long> playerProfit) {
        return playerProfit.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum() * DEALER_PROFIT_INVERSION;
    }
}
