package blackjack.domain;

import static blackjack.domain.ParticipantResult.LOSE;
import static blackjack.domain.ParticipantResult.PUSH;
import static blackjack.domain.ParticipantResult.WIN;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResults {

    private final Map<String, ParticipantResult> results;

    public PlayerResults() {
        this.results = new LinkedHashMap<>();
    }

    public void addResultByPlayerName(String playerName, ParticipantResult playerResult) {
        results.put(playerName, playerResult);
    }

    public int computeDealerWinCount() {
        return computeDealerResultCount(LOSE);
    }

    public int computeDealerPushCount() {
        return computeDealerResultCount(PUSH);
    }

    public int computeDealerLoseCount() {
        return computeDealerResultCount(WIN);
    }

    private int computeDealerResultCount(ParticipantResult participantResult) {
        return (int) results.values()
                .stream()
                .filter(result -> result == participantResult)
                .count();
    }

    public Map<String, ParticipantResult> getResults() {
        return results;
    }
}
