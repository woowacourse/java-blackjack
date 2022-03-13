package blackjack.domain.result;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchResult {

    private final Map<MatchStatus, Integer> resultOfDealer;
    private final Map<String, MatchStatus> resultOfPlayers;

    public MatchResult(final Map<String, MatchStatus> resultOfPlayers) {
        this.resultOfDealer = calculateStatusCountsOfDealer(resultOfPlayers);
        this.resultOfPlayers = new LinkedHashMap<>(resultOfPlayers);
    }

    private Map<MatchStatus, Integer> calculateStatusCountsOfDealer(final Map<String, MatchStatus> playerResult) {
        final Map<MatchStatus, Integer> dealerMatchStatusCounts = new EnumMap<>(MatchStatus.class);
        dealerMatchStatusCounts.put(MatchStatus.WIN, Math.toIntExact(MatchStatus.LOSS.countMatchStatus(playerResult)));
        dealerMatchStatusCounts.put(MatchStatus.LOSS, Math.toIntExact(MatchStatus.WIN.countMatchStatus(playerResult)));
        return dealerMatchStatusCounts;
    }

    public Map<MatchStatus, Integer> getResultOfDealer() {
        return Collections.unmodifiableMap(new EnumMap<>(resultOfDealer));
    }

    public Map<String, MatchStatus> getResultOfPlayers() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(resultOfPlayers));
    }

}
