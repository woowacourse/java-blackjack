package domain.Bet;

import domain.blackjack.WinStatus;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetResult {

    private final Map<Player, Double> betAmountByParticipant;

    private BetResult(final Map<Player, Double> result) {
        betAmountByParticipant = result;
    }

    public static BetResult of(Players players, Dealer dealer) {
        Map<Player, Double> result = new LinkedHashMap<>();
        for (Player player : players.getValue()) {
            WinStatus winStatus = WinStatus.winStatusByPlayer(player, dealer);
            result.put(player, player.profit(winStatus));
        }
        return new BetResult(result);
    }

    public double calculateDealerProfit() {
        double participantTotalProfit = betAmountByParticipant.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return -participantTotalProfit;
    }

    public Map<Player, Double> getBetAmountByParticipant() {
        return betAmountByParticipant;
    }
}
