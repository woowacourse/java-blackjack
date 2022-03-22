package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackProfitResult {

    private final Dealer dealer;
    private final List<Player> players;

    public BlackjackProfitResult(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<Participant, Double> calculateProfitResult(Map<Player, BlackjackMatch> playersMatchResult) {
        final Map<Participant, Double> result = new LinkedHashMap<>();

        final Map<Player, Double> playersProfit = calculatePlayersProfit(playersMatchResult);
        final Double dealerProfit = calculateDealerProfit(playersProfit);
        result.put(dealer, dealerProfit);
        result.putAll(playersProfit);

        return result;
    }

    private Double calculateDealerProfit(Map<Player, Double> playersProfitResult) {
        double dealerProfit = 0;
        for (Double blackjackProfit : playersProfitResult.values()) {
            dealerProfit -= blackjackProfit;
        }
        return dealerProfit;
    }

    private Map<Player, Double> calculatePlayersProfit(Map<Player, BlackjackMatch> result) {
        final Map<Player, Double> playersProfitResult = new LinkedHashMap<>();
        for (Player player : players) {
            final BlackjackMatch match = result.get(player);
            final double profit = player.calculateProfit(match);
            playersProfitResult.put(player, profit);
        }
        return playersProfitResult;
    }
}
