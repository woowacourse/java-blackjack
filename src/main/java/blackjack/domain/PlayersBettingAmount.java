package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.player.BettingAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;

public class PlayersBettingAmount {

    private final Map<Player, BettingAmount> profitResult;

    public PlayersBettingAmount(Map<Player, BettingAmount> profitResult) {
        this.profitResult = profitResult;
    }

    public Map<Participant, Integer> getResult(Dealer dealer) {
        Map<Participant, Integer> result = new LinkedHashMap<>();
        Participant copyDealer = dealer.copy();
        result.put(copyDealer, 0);
        for (Player participant : profitResult.keySet()) {
            int profit = getProfit(participant, dealer);
            result.put(participant.copy(), profit);
            result.merge(copyDealer, -profit, Integer::sum);
        }
        return result;
    }

    private int getProfit(Player participant, Dealer dealer) {
        BettingAmount bettingAmount = profitResult.get(participant);
        return (int)bettingAmount.getDividend(participant.compete(dealer));
    }
}
