package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Results {

    private static final String NO_SUCH_DEALER_ERROR_MESSAGE = "딜러가 없습니다.";

    private final Map<Player, OutcomeResults> results;

    public Results(Map<Player, OutcomeResults> results) {
        this.results = results;
    }

    public OutcomeResults getDealerResult() {
        Player dealer = findDealer();
        return results.get(dealer);
    }

    public Map<Player, OutcomeResults> getParticipantsResults() {
        Player dealer = findDealer();
        Map<Player, OutcomeResults> newResults = new LinkedHashMap<>(results);
        newResults.remove(dealer);
        return Collections.unmodifiableMap(newResults);
    }

    private Player findDealer() {
        return results.keySet().stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(NO_SUCH_DEALER_ERROR_MESSAGE));
    }
}
