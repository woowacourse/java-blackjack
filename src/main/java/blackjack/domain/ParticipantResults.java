package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class ParticipantResults {
    private final Map<String, ResultType> playerNameToResultType = new HashMap<>();

    void tiePlayer(final String playerName) {
        playerNameToResultType.put(playerName, ResultType.TIE);
    }

    void winPlayer(final String playerName) {
        playerNameToResultType.put(playerName, ResultType.WIN);
    }

    void losePlayer(final String playerName) {
        playerNameToResultType.put(playerName, ResultType.LOSE);
    }

    public Map<String, ResultType> getPlayerNameToResultType() {
        return Map.copyOf(playerNameToResultType);
    }
}
