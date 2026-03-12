package blackjack.domain.betting;

import blackjack.domain.Participants;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BettingProfitCalculator {

    private static final int DEALER_PROFIT_INVERSION = -1;

    public BettingProfit calculate(Participants participants) {
        Dealer dealer = participants.getDealer();

        Map<Participant, Long> playerProfit = calculatePlayersProfit(dealer, participants.getPlayers());
        long dealerProfit = calculateDealerProfit(playerProfit);

        return new BettingProfit(playerProfit, dealerProfit);
    }

    private Map<Participant, Long> calculatePlayersProfit(Dealer dealer, List<Player> players) {
        Map<Participant, Long> playerProfit = new LinkedHashMap<>();

        for (Player player : players) {
            BettingResult bettingResult = BettingResult.judge(dealer, player);
            long profitRate = player.calculateProfitRate(bettingResult);
            playerProfit.put(player, profitRate);
        }
        return playerProfit;
    }

    private long calculateDealerProfit(Map<Participant, Long> playerProfit) {
        return playerProfit.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum() * DEALER_PROFIT_INVERSION;
    }
}
