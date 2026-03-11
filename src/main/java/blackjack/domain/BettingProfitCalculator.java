package blackjack.domain;

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

        Map<Participant, Integer> playerProfit = calculatePlayersProfit(dealer, participants.getPlayers());
        int dealerProfit = calculateDealerProfit(playerProfit);

        return new BettingProfit(playerProfit, dealerProfit);
    }

    private Map<Participant, Integer> calculatePlayersProfit(Dealer dealer, List<Player> players) {
        Map<Participant, Integer> playerProfit = new LinkedHashMap<>();

        for (Player player : players) {
            BettingResult bettingResult = BettingResult.judge(dealer, player);
            int profitRate = player.calculateProfitRate(bettingResult);
            playerProfit.put(player, profitRate);
        }
        return playerProfit;
    }

    private int calculateDealerProfit(Map<Participant, Integer> playerProfit) {
        return playerProfit.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum() * DEALER_PROFIT_INVERSION;
    }
}
