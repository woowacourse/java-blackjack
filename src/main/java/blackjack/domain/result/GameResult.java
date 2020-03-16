package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Participants;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    private static final int INITIAL_COUNT = 0;
    private static final Map<Outcome, Integer> initialDealerResults;

    static {
        Map<Outcome, Integer> dealerResults = new LinkedHashMap<>();
        List<Outcome> dealerOutcomes = Arrays.asList(Outcome.values());
        Collections.reverse(dealerOutcomes);
        for (Outcome outcome : dealerOutcomes) {
            dealerResults.put(outcome, INITIAL_COUNT);
        }
        initialDealerResults = dealerResults;
    }

    private final Map<Player, Outcome> playerResults;
    private final Map<Outcome, Integer> dealerResults;

    public GameResult(Participants participants) {
        this.playerResults = Collections.unmodifiableMap(calculatePlayerResults(participants));
        this.dealerResults = Collections.unmodifiableMap(calculateDealerResults());
    }

    private Map<Player, Outcome> calculatePlayerResults(Participants participants) {
        Dealer dealer = participants.getDealer();
        return participants.getPlayers().stream()
            .collect(Collectors
                .toMap(player -> player, player -> player.calculateOutcome(dealer), (a1, a2) -> a1,
                    LinkedHashMap::new));
    }

    private Map<Outcome, Integer> calculateDealerResults() {
        Map<Outcome, Integer> dealerResults = initialDealerResults;
        for (Outcome outcome : this.playerResults.values()) {
            dealerResults.put(outcome, dealerResults.get(outcome) + 1);
        }
        return dealerResults;
    }

    public Map<Player, Outcome> getPlayersResult() {
        return playerResults;
    }

    public Map<Outcome, Integer> getDealerResultsNoZero() {
        return Collections.unmodifiableMap(dealerResults.keySet().stream()
            .filter(outcome -> dealerResults.get(outcome) != 0)
            .collect(Collectors.toMap(outcome -> outcome, dealerResults::get)));
    }
}
