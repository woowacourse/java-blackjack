package blackjack.domain.game.betting;

import static blackjack.domain.participant.Dealer.DEALER_NAME;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Profits {
    private final Map<String, Integer> participantsProfit;

    public Profits(final Map<String, Integer> playersProfit) {
        final Map<String, Integer> participantsProfit = new LinkedHashMap<>();
        participantsProfit.put(DEALER_NAME.getValue(), calculateDealerProfit(playersProfit.values()));
        participantsProfit.putAll(playersProfit);
        this.participantsProfit = participantsProfit;
    }

    private int calculateDealerProfit(final Collection<Integer> playerResults) {
        int totalPlayersProfit = playerResults.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return totalPlayersProfit * -1;
    }

    public Map<String, Integer> getAllProfits() {
        return participantsProfit;
    }
}
