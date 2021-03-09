package blackjack.domain;

import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    public int calculateDealerResult(final Participant dealer, final List<Participant> players,
        final Result result) {
        return (int) players.stream()
            .filter(player -> dealer.decideWinner(player).isSameResult(result))
            .count();
    }

    public Map<Name, Result> makePlayerResults(final List<Participant> players,
        final Participant dealer) {
        final Map<Name, Result> results = new LinkedHashMap<>();
        for (final Participant player : players) {
            final Result result = player.decideWinner(dealer);
            results.put(player.getName(), result);
        }
        return results;
    }

}
