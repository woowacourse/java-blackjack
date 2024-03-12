package model.result;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayersResult {

    private final Map<String, ResultStatus> results;

    private PlayersResult(Map<String, ResultStatus> results) {
        this.results = Collections.unmodifiableMap(results);
    }

    public static PlayersResult from(GameResult gameResult) {
        Map<String, ResultStatus> result = new HashMap<>();
        ParticipantScore dealerScore = gameResult.getDealerScore();

        for (ParticipantScore playerScore : gameResult.getPlayersScore()) {
            ResultStatus resultStatus = gameResult.decideResultStatus(playerScore, dealerScore);
            result.put(playerScore.getParticipantName(), resultStatus);
        }
        return new PlayersResult(result);
    }

    public Set<String> allPlayerName() {
        return results.keySet();
    }

    public ResultStatus findPlayerResult(String playerName) {
        return results.get(playerName);
    }
}
