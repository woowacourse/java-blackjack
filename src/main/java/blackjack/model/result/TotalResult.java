package blackjack.model.result;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalResult {

    private static final String DEALER_RESULT_FORMAT = "%d승 %d무 %d패";
    private static final String PLAYER_RESULT_FORMAT = "%s: %s";

    private final Map<Player, Result> results;

    private TotalResult(Map<Player, Result> results) {
        if (results == null || results.isEmpty()) {
            throw new IllegalArgumentException("results가 null이거나 비어있습니다.");
        }

        this.results = results;
    }

    public static TotalResult of(
            List<Player> players,
            Dealer dealer
    ) {
        Map<Player, Result> results = new HashMap<>();

        players.forEach(
                        player -> results.put(
                                player,
                                dealer.determineResultOf(player)
                        ));

        return new TotalResult(results);
    }

    public String getDealerResult() {
        return String.format(
               DEALER_RESULT_FORMAT,
                countOf(Result.LOSE),
                countOf(Result.DRAW),
                countOf(Result.WIN)
        );
    }

    private long countOf(Result target) {
        return results.values().stream()
                .filter(result -> result == target)
                .count();
    }

    public List<String> getPlayerResults() {
        return results.entrySet().stream()
                .map(
                        entry -> String.format(
                                PLAYER_RESULT_FORMAT,
                                entry.getKey().getName(),
                                entry.getValue().getDisplayName())
                ).toList();
    }
}
