package domain;

import java.util.EnumMap;
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
            System.out.println(participant.getName() + " " + participant.getHandValue());
            scores.put(participant, participant.isBust());
        }

        return scores;
    }

    public Map<Result, Integer> getDealerStatus(Map<String, Result> results) {
        EnumMap<Result, Integer> DealerWinningStatus = new EnumMap<>(Result.class);

        for (Result playerResult : results.values()) {
            judgeResult(DealerWinningStatus, playerResult);
        }
        return DealerWinningStatus;
    }

    private void judgeResult(EnumMap<Result, Integer> result, Result playerResult) {
        if (playerResult.equals(Result.WIN)) {
            result.put(Result.LOSE, result.getOrDefault(Result.LOSE, 0) + 1);
        }
        if (playerResult.equals(Result.TIE)) {
            result.put(Result.TIE, result.getOrDefault(Result.TIE, 0) + 1);
        }
        if (playerResult.equals(Result.LOSE)) {
            result.put(Result.WIN, result.getOrDefault(Result.WIN, 0) + 1);
        }
    }
}
