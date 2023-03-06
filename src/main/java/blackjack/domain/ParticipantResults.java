package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

class ParticipantResults {

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

    Map<String, ResultType> getPlayerNameToResultType() {
        return playerNameToResultType;
    }
}
