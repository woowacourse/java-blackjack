package blackjack.domain.result;

import java.util.EnumMap;
import java.util.Map;

public class MatchResult {

    private final Map<MatchStatus, Integer> resultOfDealer;
    private final Map<String, MatchStatus> resultOfPlayers;

    public MatchResult(final Map<String, MatchStatus> resultOfPlayers) {
        this.resultOfDealer = calculateDealerResult(resultOfPlayers);
        this.resultOfPlayers = resultOfPlayers;
    }

    private Map<MatchStatus, Integer> calculateDealerResult(final Map<String, MatchStatus> playerResult) {
        final Map<MatchStatus, Integer> playerMatchStatusCounts = new EnumMap<>(MatchStatus.class);
        for (final MatchStatus result : playerResult.values()) {
            playerMatchStatusCounts.merge(result, 1, Integer::sum);
        }
        return reverseMatchStatusCounts(playerMatchStatusCounts);
    }

    private Map<MatchStatus, Integer> reverseMatchStatusCounts(final Map<MatchStatus, Integer> targetMatchStatusCounts) {
        final Map<MatchStatus, Integer> matchStatusCounts = new EnumMap<>(MatchStatus.class);
        matchStatusCounts.put(MatchStatus.WIN, targetMatchStatusCounts.getOrDefault(MatchStatus.LOSS, 0));
        matchStatusCounts.put(MatchStatus.LOSS, targetMatchStatusCounts.getOrDefault(MatchStatus.WIN, 0));
        return matchStatusCounts;
    }

    public Map<MatchStatus, Integer> getResultOfDealer() {
        return Map.copyOf(resultOfDealer);
    }

    public Map<String, MatchStatus> getResultOfPlayers() {
        return Map.copyOf(resultOfPlayers);
    }

}
