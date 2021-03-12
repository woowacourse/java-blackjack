package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.*;

public class GameResult {
    private final List<Integer> resultCounts;
    private final Map<Player, Result> playersResult;

    public GameResult(final Dealer dealer, final List<Player> players) {
        playersResult = findPlayersResult(dealer, players);
        resultCounts = findResultCounts();
    }

    private Map<Player, Result> findPlayersResult(final Dealer dealer, final List<Player> players) {
        final Map<Player, Result> playersResult = new HashMap<>();
        for (final Player player : players) {
            playersResult.put(player, player.findResult(dealer));
        }
        return playersResult;
    }

    private List<Integer> findResultCounts() {
        final List<Integer> resultCounts = new ArrayList<>();
        for (Result result : Result.values()) {
            resultCounts.add(countResults(result));
        }
        return resultCounts;
    }

    private int countResults(final Result result) {
        return (int) playersResult.entrySet().stream().filter(map -> map.getValue() == result).count();
    }

    public Map<Player, Result> getPlayersResult() {
        return playersResult;
    }

    public List<Integer> getResultCounts() {
        return resultCounts;
    }
}
