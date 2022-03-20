package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackProfitResult {

    private final Dealer dealer;
    private final Map<Player, BettingMoney> playersInfo;

    public BlackjackProfitResult(Dealer dealer, Map<Player, BettingMoney> playersInfo) {
        this.dealer = dealer;
        this.playersInfo = playersInfo;
    }

    public Map<Participant, Double> calculateParticipantsProfit(Map<Player, BlackjackMatch> result) {
        final Map<Participant, Double> profitResult = new LinkedHashMap<>();
        final Map<Player, Double> playersProfitResult = calculatePlayersProfit(result);
        profitResult.put(dealer, calculateDealerProfit(playersProfitResult));
        profitResult.putAll(playersProfitResult);
        return profitResult;
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
        for (Map.Entry<Player, BettingMoney> playerInfo : playersInfo.entrySet()) {
            final Player player = playerInfo.getKey();
            final BlackjackMatch match = result.get(player);
            final BettingMoney bettingMoney = playerInfo.getValue();
            final double profit = player.calculateProfit(match, bettingMoney);
            playersProfitResult.put(player, profit);
        }
        return playersProfitResult;
    }
}
