package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
    private static final Map<String, List<Outcome>> results = new LinkedHashMap<>();

    public Map<String, List<Outcome>> finishGame(Dealer dealer, List<Player> players) {
        List<Outcome> dealerResults = players.stream()
                .map(player -> Outcome.findOutcome(dealer.calculateScore(21), player.calculateScore(21)))
                .collect(Collectors.toList());
        results.put(Dealer.getName(), dealerResults);
        players.forEach(player ->
                    results.put(player.getName(), Collections.singletonList(Outcome.reverseResult(dealerResults.remove(0))))
        );
        return results;
    }
}
