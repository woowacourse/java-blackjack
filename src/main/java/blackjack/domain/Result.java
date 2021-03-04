package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Result {
    private static final Map<String, List<Outcome>> results = new LinkedHashMap<>();

    public static Map<String, List<Outcome>> finishGame(Dealer dealer, List<Player> players) {
        List<Outcome> dealerResults = players.stream()
                .map(player -> Outcome.findOutcome(dealer.calculateScore(21), player.calculateScore(21)))
                .collect(Collectors.toList());
        results.put(Dealer.getName(), new ArrayList<>(dealerResults));
        players.forEach(player ->
                    results.put(player.getName(), Collections.singletonList(Outcome.reverseResult(dealerResults.remove(0))))
        );
        return results;
    }
}
