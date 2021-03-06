package blackjack.domain;

import blackjack.domain.participants.Participant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    public int calculateDealerWinCount(final Participant dealer, final List<Participant> players) {
        return (int) players.stream()
            .filter(player -> dealer.decideWinner(player).isSameResult(Result.WIN))
            .count();
    }

    public Map<String, Result> makePlayerResults(final List<Participant> players,
        final Participant dealer) {
        final Map<String, Result> results = new LinkedHashMap<>();
        for (final Participant player : players) {
            final Result result = player.decideWinner(dealer);
            results.put(player.getName(), result);
        }
        return results;
    }

}
