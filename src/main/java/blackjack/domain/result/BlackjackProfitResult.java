package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackProfitResult {

    private final Dealer dealer;
    private final Map<Player, BettingMoney> players;

    public BlackjackProfitResult(Dealer dealer, Map<Player, BettingMoney> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<Participant, Double> calculateParticipantsProfit() {
        final Map<Participant, Double> profitResult = new LinkedHashMap<>();
        profitResult.put(dealer, calculateDealerProfit());
        for (Player player : players.keySet()) {
            final BlackjackMatch match = BlackjackMatch.calculateMatch(player, dealer);
            final double playerProfit = players.get(player).calculateProfit(match.getProfitRatio());
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
        for (Map.Entry<Player, BettingMoney> playerInfo : players.entrySet()) {
            final BlackjackMatch match = BlackjackMatch.calculateMatch(playerInfo.getKey(), dealer);
            final double profit = playerInfo.getValue().calculateProfit(match.getProfitRatio());
            playersProfitResult.put(playerInfo.getKey(), profit);
        }
        return playersProfitResult;
    }
}
