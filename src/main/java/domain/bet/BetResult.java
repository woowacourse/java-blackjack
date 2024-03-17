package domain.bet;

import domain.blackjack.WinStatus;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class BetResult {

    private final Map<Name, Double> betAmountByParticipant;

    private BetResult(final Map<Name, Double> result) {
        betAmountByParticipant = result;
    }

    public static BetResult of(Players players, Dealer dealer) {
        Map<Name, Double> result = new LinkedHashMap<>();
        for (Player player : players.getValue()) {
            WinStatus winStatus = WinStatus.winStatusOfPlayer(player, dealer);
            result.put(player.getName(), player.profit(winStatus));
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

    public Map<Name, Double> getBetAmountByParticipant() {
        return betAmountByParticipant;
    }
}
