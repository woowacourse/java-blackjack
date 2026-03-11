package blackjack.dto;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.result.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record PlayerResults(Map<String, Result> results) {

    public static PlayerResults of(List<Player> players, Dealer dealer) {
        Map<String, Result> results = new HashMap<>();

        for (Player player : players) {
            Result result = dealer.judgePlayerResult(player);
            results.put(player.getName(), result);
        }

        return new PlayerResults(results);
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
}
