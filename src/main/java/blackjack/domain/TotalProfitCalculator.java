package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TotalProfitCalculator {

    private static final int DEALER_PROFIT_INITIAL = 0;
    private static final int DEALER_PROFIT_RATE_FROM_PLAYER = -1;

    private final Map<String, Integer> totalProfitByParticipant = new LinkedHashMap<>();

    public Map<String, Integer> calculateProfitByParticipant(final Dealer dealer, final List<Player> players) {
        totalProfitByParticipant.put(dealer.getName(), DEALER_PROFIT_INITIAL);
        players.forEach(player -> add(dealer, player));
        return totalProfitByParticipant;
    }

    private void add(final Dealer dealer, final Player player) {
        final int playerProfit = dealer.computeProfitOf(player);
        totalProfitByParticipant.put(player.getName(), playerProfit);
        totalProfitByParticipant.put(dealer.getName(),
                totalProfitByParticipant.getOrDefault(dealer.getName(), DEALER_PROFIT_INITIAL)
                        + playerProfit * DEALER_PROFIT_RATE_FROM_PLAYER);
    }
}
