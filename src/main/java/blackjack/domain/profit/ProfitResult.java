package blackjack.domain.profit;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {
    private static final int INITIAL_PROFIT = 0;
    private static final int NUMBER_FOR_MAKE_REVERSE_PROFIT = -1;

    private final Map<Participant, Integer> result;

    public ProfitResult() {
        this.result = new LinkedHashMap<>();
    }

    public void recordParticipantsProfit(final Dealer dealer, final Player player, final int profit) {
        final int dealerProfit = reverse(profit);

        result.put(dealer, result.getOrDefault(dealer, INITIAL_PROFIT) + dealerProfit);
        result.put(player, profit);
    }

    private int reverse(final int profit) {
        return profit * NUMBER_FOR_MAKE_REVERSE_PROFIT;
    }

    public Map<Participant, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
