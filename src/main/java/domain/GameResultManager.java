package domain;

import java.util.LinkedHashMap;
import java.util.Map;

// TODO: 2023/03/06 딜러 승패
// TODO: 2023/03/06  플레이어 승패
public class GameResultManager {
    private final Map<Participant, Integer> gameResult;

    public GameResultManager(final Map<Participant, Integer> gameResult) {
        this.gameResult = gameResult;
    }

    public Map<Participant, Boolean> getParticipantsBustStatus() {
        Map<Participant, Boolean> scores = new LinkedHashMap<>();
        for (Participant participant : gameResult.keySet()) {
            scores.put(participant, participant.isBust());
        }

        return scores;
    }
}
