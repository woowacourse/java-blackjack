package blackjack.model.result;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalResult {

    private final Map<Player, Result> results;

    public TotalResult(Map<Player, Result> results) {
        if (results == null) {
            throw new IllegalArgumentException("results가 null입니다.");
        }

        this.results = results;
    }

    public static TotalResult of(List<Player> players, Dealer dealer) {
        Map<Player, Result> results = new HashMap<>();

        for (Player player : players) {
            Result result = dealer.judgePlayerResult(player);
            results.put(player, result);
        }

        return new TotalResult(results);
    }

    public String getDealerResult() {
        return String.format(
                "%d승 %d무 %d패",
                countOf(Result.LOSE), countOf(Result.DRAW), countOf(Result.WIN)
        );
    }

    private long countOf(Result target) {
        return results.values().stream()
                .filter(result -> result == target)
                .count();
    }

    public List<String> getPlayerResults() {
        return results.entrySet().stream()
                .map(entry -> String.format("%s: %s", entry.getKey().getName(), entry.getValue().getDisplayName()))
                .toList();
    }
}
