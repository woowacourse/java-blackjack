package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TotalProfitCalculator {

    private final Map<String, Integer> totalProfitByParticipant = new LinkedHashMap<>();

    public Map<String, Integer> calculateProfitByParticipant(final Dealer dealer, final List<Player> players) {
        totalProfitByParticipant.put(dealer.getName(), 0);
        players.forEach(player -> add(dealer, player));
        return totalProfitByParticipant;
    }

    private void add(final Dealer dealer, final Player player) {
        final int playerProfit = dealer.calculateBettingMoney(player);
        totalProfitByParticipant.put(player.getName(), playerProfit);
        totalProfitByParticipant.put(dealer.getName(),
                totalProfitByParticipant.getOrDefault(dealer.getName(), 0) + playerProfit * (-1));
    }
}
