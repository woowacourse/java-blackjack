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

    public Map<Participant, Double> calculateParticipantsProfit() {
        final Map<Participant, Double> profitResult = new LinkedHashMap<>();
        profitResult.put(dealer, calculateDealerProfit());
        for (Player player : playersInfo.keySet()) {
            final BlackjackMatch match = BlackjackMatch.calculateMatch(player, dealer);
            final double playerProfit = playersInfo.get(player).calculateProfit(match.getProfitRatio());
            profitResult.put(player, playerProfit);
        }
        return profitResult;
    }

    private double calculateDealerProfit() {
        final Map<Player, Double> playersResult = calculatePlayersProfit();
        return -playersResult.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private Map<Player, Double> calculatePlayersProfit() {
        final Map<Player, Double> playersProfitResult = new LinkedHashMap<>();
        for (Map.Entry<Player, BettingMoney> playerInfo : playersInfo.entrySet()) {
            final BlackjackMatch match = BlackjackMatch.calculateMatch(playerInfo.getKey(), dealer);
            final double profit = playerInfo.getValue().calculateProfit(match.getProfitRatio());
            playersProfitResult.put(playerInfo.getKey(), profit);
        }
        return playersProfitResult;
    }
}
