package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

class ParticipantResults {

    private final Map<String, ResultType> playerNameToResultType = new HashMap<>();

    public void addPlayerResult(final String playerName, final ResultType resultType) {
        playerNameToResultType.put(playerName, resultType);
    }

    Map<String, ResultType> getPlayerNameToResultType() {
        return playerNameToResultType;
    }
}
