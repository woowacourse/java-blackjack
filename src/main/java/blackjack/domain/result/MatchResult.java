package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.*;

public class MatchResult {
    private final Map<Player, MatchResultType> matchResult;

    public MatchResult(Dealer dealer, Players players) {
        matchResult = calculateMatchResult(dealer, players);
    }

    private Map<Player, MatchResultType> calculateMatchResult(Dealer dealer, Players players) {
        Map<Player, MatchResultType> matchResult = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            matchResult.put(player, dealer.compareScore(player));
        }
        return Collections.unmodifiableMap(matchResult);
    }

    public List<Integer> getMatchResultTypeCount() {
        List<Integer> matchResultCount = new ArrayList<>();

        for (MatchResultType type : MatchResultType.values()) {
            matchResultCount.add(Collections.frequency(matchResult.values(), type));
        }
        return matchResultCount;
    }

    public Map<Player, MatchResultType> getMatchResult() {
        return new LinkedHashMap<>(matchResult);
    }
}
